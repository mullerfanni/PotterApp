package com.example.potterapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.potterapp.model.Actor
import kotlinx.coroutines.flow.Flow


@Dao
interface ActorDao {
@Query("SELECT * FROM actor")
fun getAll(): Flow<List<Actor>>

@Query("SELECT * FROM actor")
fun getCharacters(): List<Actor>

@Query("SELECT * FROM actor WHERE id = :id")
fun findById(id: String): Actor

@Insert(onConflict = OnConflictStrategy.REPLACE)
fun insertAll(actor: List<Actor>)

@Delete
fun delete(actor: Actor)

@Insert(onConflict = OnConflictStrategy.REPLACE)
fun insert(character: List<Actor>)

@Update(onConflict = OnConflictStrategy.REPLACE)
fun update(character: List<Actor>)
}