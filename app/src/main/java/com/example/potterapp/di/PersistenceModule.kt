package com.example.potterapp.di

import android.app.Application
import androidx.room.Room
import com.example.potterapp.R
import com.example.potterapp.persistence.ActorDao
import com.example.potterapp.persistence.ActorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideActorDatabase(application: Application): ActorDatabase {
        return Room
            .databaseBuilder(
                application,
                ActorDatabase::class.java,
                "potterDB"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideActorDao(actorDatabase: ActorDatabase): ActorDao {
        return actorDatabase.actorDao()
    }
}