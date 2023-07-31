package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.trailer.TrailerResponse
import com.irmak.themoviedc.model.trailer.TvTrailerResponse
import com.irmak.themoviedc.repository.TrailerRepository
import com.irmak.themoviedc.repository.TvTrailerRepository
import kotlinx.coroutines.launch

class TvTrailerViewModel(private val tvTrailerRepository: TvTrailerRepository): ViewModel() {
    private var mutableTvTrailerList: MutableLiveData<com.irmak.themoviedc.model.trailer.TvTrailerResponse?> = MutableLiveData()
    val tvTrailerList: LiveData<com.irmak.themoviedc.model.trailer.TvTrailerResponse?>
        get() = mutableTvTrailerList

    fun getTvVideo(){
        viewModelScope.launch {
            mutableTvTrailerList.value = tvTrailerRepository.getTvVideo()
        }
    }

}