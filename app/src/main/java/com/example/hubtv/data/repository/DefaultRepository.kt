package com.example.hubtv.data.repository

import com.example.hubtv.remote.ModelClass.Movies
import com.example.hubtv.remote.NetworkService
import com.example.hubtv.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject


class DefaultRepository @Inject constructor(val networkService: NetworkService ) : MainRepository {

    override fun fetchMeaning(): Flow<Resource<Movies>> {

        return object : NetworkBoundRepo<Movies>(){
            override suspend fun fetchFromRemote(): Response<Movies> {
                return networkService.getData()
            }

        }.asFlow()
    }

}