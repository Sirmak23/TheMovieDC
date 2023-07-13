package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.TvPopularRepository
import com.irmak.themoviedc.viewModel.ViewModelSub.TvPopularViewModel

@Suppress("UNCHECKED_CAST")
class TvPopularViewModelFactory (private val repository: TvPopularRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvPopularViewModel::class.java)){
            return TvPopularViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Wrong View Model")
    }
}