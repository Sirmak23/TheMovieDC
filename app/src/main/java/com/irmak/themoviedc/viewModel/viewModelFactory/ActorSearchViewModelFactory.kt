package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.ActorMovieRepository
import com.irmak.themoviedc.repository.ActorSearchRepository
import com.irmak.themoviedc.viewModel.ViewModelSub.ActorMovieViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.ActorSearchViewModel

class ActorSearchViewModelFactory (private val repository: ActorSearchRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActorSearchViewModel::class.java)){
            return ActorSearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Wrong view model")
    }
}