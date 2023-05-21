package com.example.potterapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.potterapp.persistence.HouseTypeConverter
import kotlinx.serialization.*


@Serializable
@Entity
@TypeConverters(HouseTypeConverter::class)
data class Actor (
    @PrimaryKey val id: String,
    val name: String,
    val species: String,
    val house: House,
    val dateOfBirth: String? = null,
    val ancestry: String,
    val patronus: String,
    val actor: String,
    val image: String
)
