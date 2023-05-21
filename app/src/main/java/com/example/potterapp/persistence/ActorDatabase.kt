package com.example.potterapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.potterapp.model.Actor

@Database(
    entities = [Actor::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(HouseTypeConverter::class)
abstract class ActorDatabase : RoomDatabase() {
    abstract fun actorDao(): ActorDao
}