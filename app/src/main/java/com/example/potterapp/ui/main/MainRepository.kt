package com.example.potterapp.ui.main

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.potterapp.model.Actor
import com.example.potterapp.network.PotterService
import com.example.potterapp.persistence.ActorDao
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MainRepository @Inject constructor(
    private val service: PotterService,
    private val dao: ActorDao) {
    @WorkerThread
    fun getActors(): Flow<List<Actor>> = dao.getAll()

    @WorkerThread
    suspend fun reloadActors(errorMessage: MutableStateFlow<String?>, isRefreshing: MutableStateFlow<Boolean>) {
        isRefreshing.emit(true)
        try {
            // request API network call asynchronously.
            val characters = service.getActors()
            // handle the case when the API request gets a success response.
            dao.insertAll(characters)
            errorMessage.emit(null)
            isRefreshing.emit(false)
        } catch(error: Exception) {
            Log.e("something went wrong", error.message.orEmpty())

            // handle the case when the API request is fails.
            // e.g. internal server error
            val message = if (error.message?.startsWith("Unable to resolve") == true)
                "Please check your internet connection, and try again!"
            else error.message
            errorMessage.emit(message ?: "Unknown error, try again!")
            isRefreshing.emit(false)
        }
    }

    @WorkerThread
    fun deleteCharacter(it: Actor) {
        dao.delete(it)
    }
}