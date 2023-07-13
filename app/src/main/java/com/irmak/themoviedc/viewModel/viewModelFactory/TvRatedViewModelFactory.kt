package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.TvRatedRepository
import com.irmak.themoviedc.viewModel.ViewModelSub.TvRatedViewModel

class TvRatedViewModelFactory(private val repository: TvRatedRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvRatedViewModel::class.java)){
            return TvRatedViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Wrong View Model")
    }
}