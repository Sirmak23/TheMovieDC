package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.repository.WatchProviderRepository
import kotlinx.coroutines.launch

class WatchProvideViewModel (private val watchProviderRepository: WatchProviderRepository): ViewModel() {
    private val mutableProviderList: MutableLiveData<com.irmak.themoviedc.model.watchProviders.MovieProviderDetails> = MutableLiveData()
    val providerList: LiveData<com.irmak.themoviedc.model.watchProviders.MovieProviderDetails>
        get() = mutableProviderList


    fun getProviders() {
        viewModelScope.launch {
            mutableProviderList.value = watchProviderRepository.getProviders()
        }
    }

}
