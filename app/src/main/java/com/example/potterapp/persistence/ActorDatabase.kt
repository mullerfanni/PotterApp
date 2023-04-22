package com.example.potterapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.potterapp.model.Actor

@Database(entities = [Actor::class], version = 1, exportSchema = false)
abstract class ActorDatabase : RoomDatabase() {
abstract fun actorDao(): ActorDao
}