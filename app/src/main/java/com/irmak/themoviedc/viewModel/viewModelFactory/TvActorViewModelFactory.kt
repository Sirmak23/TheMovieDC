package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.model.tvActorModel.TvActorModel
import com.irmak.themoviedc.repository.ActorRepository
import com.irmak.themoviedc.repository.TvActorRepository
import com.irmak.themoviedc.viewModel.ActorViewModel
import com.irmak.themoviedc.viewModel.TvActorViewModel

@Suppress("UNCHECKED_CAST")
class TvActorViewModelFactory(private val repository: TvActorRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvActorViewModel::class.java)){
            return TvActorViewModel(repository) as T
        }
        throw IllegalArgumentException("Wrong view model")
    }
}