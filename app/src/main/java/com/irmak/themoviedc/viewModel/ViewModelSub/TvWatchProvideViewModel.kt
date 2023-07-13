package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.watchProviders.MovieProviderDetails
import com.irmak.themoviedc.model.watchProviders.TvMovieProviderDetails
import com.irmak.themoviedc.repository.TvWatchProviderRepository
import com.irmak.themoviedc.repository.WatchProviderRepository
import kotlinx.coroutines.launch

class TvWatchProvideViewModel  (private val tvWatchProviderRepository: TvWatchProviderRepository): ViewModel() {
    private val mutableTvProviderList: MutableLiveData<TvMovieProviderDetails> = MutableLiveData()
    val tvProviderList: LiveData<TvMovieProviderDetails>
        get() = mutableTvProviderList

    fun getTvProviders(){
        viewModelScope.launch {
            mutableTvProviderList.value = tvWatchProviderRepository.getTvProviders()
        }
    }
}