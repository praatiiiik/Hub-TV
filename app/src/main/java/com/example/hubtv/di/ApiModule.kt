package com.example.hubtv.di

import com.example.hubtv.remote.NetworkService
import com.example.hubtv.remote.Networking
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Provides Network Service Instance
 */

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun providesNetworkService() : NetworkService = Networking.createRetrofitInstance()

}