package com.example.potterapp.di

import com.example.potterapp.network.PotterService
import com.example.potterapp.persistence.ActorDao
import com.example.potterapp.ui.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMainRepository(
        potterService: PotterService,
        potterDao: ActorDao
    ): MainRepository {
        return MainRepository(potterService, potterDao)
    }
}