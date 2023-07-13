package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.TvTrailerRepository
import com.irmak.themoviedc.viewModel.ViewModelSub.TvTrailerViewModel

@Suppress("UNCHECKED_CAST")
class TvTrailerViewModelFactory(private val repository: TvTrailerRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvTrailerViewModel::class.java)){
            return TvTrailerViewModel(repository) as T
        }
        throw IllegalArgumentException("wrong view model")
    }
}