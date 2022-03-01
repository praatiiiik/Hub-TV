package com.example.hubtv.utils

import android.os.Message

sealed class Resource<T> {
    data class Success<T>(val data : T): Resource<T>()
    data class Failed<T>(val message: String) : Resource<T>()
}