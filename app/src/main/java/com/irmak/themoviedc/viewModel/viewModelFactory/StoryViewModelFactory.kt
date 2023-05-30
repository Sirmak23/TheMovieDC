package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.StoryRepository
import com.irmak.themoviedc.viewModel.StoryViewModel


@Suppress("UNCHECKED_CAST")
class StoryViewModelFactory(private val repository: StoryRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoryViewModel::class.java)){
            return StoryViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("wrong view model")
    }
}