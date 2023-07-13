package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.TvWatchProviderRepository
import com.irmak.themoviedc.viewModel.ViewModelSub.TvWatchProvideViewModel

@Suppress("UNCHECKED_CAST")
class TvWatchProvideViewModelFactory (private val repository: TvWatchProviderRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvWatchProvideViewModel::class.java)){
            return TvWatchProvideViewModel(repository) as T
        }
        throw IllegalArgumentException("Wrong view model")
    }
}