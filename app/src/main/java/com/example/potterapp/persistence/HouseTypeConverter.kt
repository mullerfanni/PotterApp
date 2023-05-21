package com.example.potterapp.persistence

import androidx.room.TypeConverter
import com.example.potterapp.model.House
import kotlinx.serialization.json.Json

class HouseTypeConverter {
    @TypeConverter
    fun fromHouse(value: House?): String? {
        return value?.let { Json.encodeToString(House.serializer(), it) }
    }

    @TypeConverter
    fun stringToHouse(house: String?): House? {
        return house?.let { Json.decodeFromString(House.serializer(), it) }
    }
}
