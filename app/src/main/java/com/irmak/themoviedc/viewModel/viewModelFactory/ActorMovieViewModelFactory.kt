package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.ActorMovieRepository
import com.irmak.themoviedc.viewModel.ViewModelSub.ActorMovieViewModel

class ActorMovieViewModelFactory (private val repository: ActorMovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActorMovieViewModel::class.java)){
            return ActorMovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Wrong view model")
    }
}