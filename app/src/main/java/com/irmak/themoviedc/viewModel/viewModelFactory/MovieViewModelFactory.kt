package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.PopularListRepository
import com.irmak.themoviedc.viewModel.MovieViewModel

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(private val repository: PopularListRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)){
            return MovieViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Wrong View Model")
    }
}