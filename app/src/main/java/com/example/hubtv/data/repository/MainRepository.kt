package com.example.hubtv.data.repository

import com.example.hubtv.remote.ModelClass.Movies
import com.example.hubtv.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Repository Layer for processing data.
 */
interface MainRepository {

    fun fetchMeaning(): Flow<Resource<Movies>>

}