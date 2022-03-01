package com.example.hubtv.data.repository

import androidx.annotation.WorkerThread
import com.example.hubtv.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class NetworkBoundRepo<RESULT> {

    @WorkerThread
    abstract suspend fun fetchFromRemote() : Response<RESULT>


    fun asFlow() = flow<Resource<RESULT>> {

        val apiResponse = fetchFromRemote()

        val remoteData = apiResponse.body()

        if(apiResponse.isSuccessful && remoteData!=null){
            emit(Resource.Success(remoteData))
        }else{
            emit(Resource.Failed("Error"))
        }

    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Failed("Network error!"))
    }

}