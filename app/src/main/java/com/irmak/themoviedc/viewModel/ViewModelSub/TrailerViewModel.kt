package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.trailer.TrailerResponse
import com.irmak.themoviedc.repository.TrailerRepository
import kotlinx.coroutines.launch

class TrailerViewModel(private val trailerRepository: TrailerRepository): ViewModel() {
    private var mutableTrailerList: MutableLiveData<TrailerResponse?> = MutableLiveData()
    val trailerList: LiveData<TrailerResponse?>
        get() = mutableTrailerList

    fun getVideo(){
        viewModelScope.launch {
            mutableTrailerList.value = trailerRepository.getVideo()
        }
    }

}