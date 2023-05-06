package com.example.potterapp.ui.main

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.potterapp.model.Actor
import com.example.potterapp.model.UIModel
import com.example.potterapp.network.PotterService
import com.example.potterapp.persistence.ActorDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val service: PotterService,
    private val dao: ActorDao) {
    @WorkerThread
    fun getActors(): Flow<List<Actor>> = dao.getAll()

    @WorkerThread
    suspend fun reloadActors(state: MutableStateFlow<UIModel>) {
        state.emit(UIModel.Loading)
        try {
            // request API network call asynchronously.
            val characters = service.getActors()
            // handle the case when the API request gets a success response.
            dao.insertAll(characters)
            state.emit(UIModel.Loaded)
        } catch(error: Exception) {
            Log.e("something went wrong", error.message.orEmpty())

            // handle the case when the API request is fails.
            // e.g. internal server error
            state.emit(UIModel.Error(error.message ?: "error"))
        }
    }

    @WorkerThread
    fun deleteCharacter(it: Actor) {
        dao.delete(it)
    }
}