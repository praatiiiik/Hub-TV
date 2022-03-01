package com.example.hubtv.di

import com.example.hubtv.data.repository.DefaultRepository
import com.example.hubtv.data.repository.MainRepository
import com.example.hubtv.remote.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Provides Main Repository Instance.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesMainRepo(networkService: NetworkService):MainRepository = DefaultRepository(networkService)
}