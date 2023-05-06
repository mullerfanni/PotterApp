package com.example.potterapp.persistence

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.potterapp.model.Actor
import com.example.potterapp.rules.CoroutineTestRule
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33],manifest=Config.NONE)
class LocalDatabase {
lateinit var db: ActorDatabase
private lateinit var appDao: ActorDao

@get:Rule
val coroutinesRule = CoroutineTestRule()

@Before
fun initDB() {
db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), ActorDatabase::class.java)
.allowMainThreadQueries()
.build()
appDao = db.actorDao()
}

@After
fun closeDB() {
db.close()
}


@Test
fun insertAndLoadTest() = runTest {
val mockDataList = listOf(
Actor(
    id = "1",
    name = "Fanni",
    species = "ember",
    house = "griffendor",
    dateOfBirth = "1999-05-10",
    ancestry = "half-blood",
    patronus = "stag",
    actor = "Müller Fanni",
    image = ""
),
Actor(
    id = "2",
    name = "Panni",
    species = "ember",
    house = "griffendel",
    dateOfBirth = "2000-05-10",
    ancestry = "half-blood",
    patronus = "stag",
    actor = "Valaki Panni",
    image = ""
)
)
appDao.insertAll(mockDataList)

val loadFromDB = appDao.getCharacters()
assert(loadFromDB.size == 2)

val mockData = appDao.findById("1")
assert(loadFromDB.firstOrNull { it == mockData } != null)
}
}