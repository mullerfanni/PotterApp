package com.example.potterapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.*


@Serializable
@Entity
data class Actor (
    @PrimaryKey val id: String,
    val name: String,
    val species: String,
    val house: String,
    val dateOfBirth: String,
    val ancestry: String,
    val patronus: String,
    val actor: String,
    val image: String
)
