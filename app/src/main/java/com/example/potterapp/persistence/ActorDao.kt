package com.example.potterapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.potterapp.model.Actor

@Dao
interface ActorDao {
@Query("SELECT * FROM actor")
fun getAll(): List<Actor>

@Query("SELECT * FROM actor WHERE id = :id")
fun findById(id: String): Actor

@Insert
fun insertAll(vararg actor: Actor)

@Delete
fun delete(actor: Actor)
}