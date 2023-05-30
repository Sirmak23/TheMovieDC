package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.NowPlayingRepository
import com.irmak.themoviedc.viewModel.NowPlayingViewModel

@Suppress("UNCHECKED_CAST")
class NowPlayingViewModelFactory(private val repository: NowPlayingRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NowPlayingViewModel::class.java)){
            return NowPlayingViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("wrong view model")
    }
}