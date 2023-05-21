package com.example.potterapp.ui.details

import androidx.annotation.WorkerThread
import com.example.potterapp.model.Actor
import com.example.potterapp.network.PotterService
import com.example.potterapp.persistence.ActorDao
import javax.inject.Inject

class DetailsRepository @Inject constructor(private val dao: ActorDao) {

    @WorkerThread
    fun getCharacter(id: String?): Actor? = dao.findById(id)

}