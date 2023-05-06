package com.example.potterapp.network

import com.example.potterapp.model.Actor
import retrofit2.http.GET

interface PotterService {
    @GET("characters")
    suspend fun getActors(): List<Actor>
}