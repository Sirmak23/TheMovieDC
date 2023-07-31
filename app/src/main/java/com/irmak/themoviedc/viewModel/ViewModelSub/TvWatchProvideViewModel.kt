package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.watchProviders.TvMovieProviderDetails
import com.irmak.themoviedc.repository.TvWatchProviderRepository
import kotlinx.coroutines.launch

class TvWatchProvideViewModel  (private val tvWatchProviderRepository: TvWatchProviderRepository): ViewModel() {
    private val mutableTvProviderList: MutableLiveData<com.irmak.themoviedc.model.watchProviders.TvMovieProviderDetails> = MutableLiveData()
    val tvProviderList: LiveData<com.irmak.themoviedc.model.watchProviders.TvMovieProviderDetails>
        get() = mutableTvProviderList

    fun getTvProviders(){
        viewModelScope.launch {
            mutableTvProviderList.value = tvWatchProviderRepository.getTvProviders()
        }
    }
}