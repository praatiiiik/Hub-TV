package com.example.hubtv.remote

import com.example.hubtv.remote.ModelClass.Movies
import com.example.hubtv.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

/**
 * Calling function with end point to fetch data from server
 */
interface NetworkService {

    @GET(Constants.END_POINT)
    suspend fun getData(): Response<Movies>

}