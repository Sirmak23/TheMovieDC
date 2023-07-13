package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.WatchProviderRepository
import com.irmak.themoviedc.viewModel.ViewModelSub.WatchProvideViewModel

class WatchProvideViewModelFactory (private val repository: WatchProviderRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WatchProvideViewModel::class.java)){
            return WatchProvideViewModel(repository) as T
        }
        throw IllegalArgumentException("Wrong view model")
    }
}