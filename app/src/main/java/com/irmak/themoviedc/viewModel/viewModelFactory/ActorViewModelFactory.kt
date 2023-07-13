package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.ActorRepository
import com.irmak.themoviedc.viewModel.ViewModelSub.ActorViewModel

@Suppress("UNCHECKED_CAST")
class ActorViewModelFactory(private val repository: ActorRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActorViewModel::class.java)){
            return ActorViewModel(repository) as T
        }
        throw IllegalArgumentException("Wrong view model")
    }
}