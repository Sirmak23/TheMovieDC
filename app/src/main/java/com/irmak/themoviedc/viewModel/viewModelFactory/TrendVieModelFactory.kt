package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.ActorMovieRepository
import com.irmak.themoviedc.repository.TrendRepository
import com.irmak.themoviedc.viewModel.ViewModelSub.ActorMovieViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.TrendViewModel

@Suppress("UNCHECKED_CAST")
class TrendVieModelFactory (private val repository: TrendRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrendViewModel::class.java)){
            return TrendViewModel(repository) as T
        }
        throw IllegalArgumentException("Wrong view model")
    }
}