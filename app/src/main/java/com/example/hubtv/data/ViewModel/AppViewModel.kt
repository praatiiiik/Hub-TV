package com.example.hubtv.data.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hubtv.data.repository.MainRepository
import com.example.hubtv.remote.ModelClass.Movies
import com.example.hubtv.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * App View model
 */
@HiltViewModel
class AppViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

    private val _movies : MutableLiveData<Status<Movies>> = MutableLiveData()
    val movies : LiveData<Status<Movies>> = _movies

    /**
     * taking data from repository and updating livedata state.
     */
    fun getMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.fetchMeaning().onStart {
                _movies.postValue(Status.loading())
            }.map { resource ->
                Status.fromResource(resource)
            }.collect { status ->
                _movies.postValue(status)
            }
        }
    }

}