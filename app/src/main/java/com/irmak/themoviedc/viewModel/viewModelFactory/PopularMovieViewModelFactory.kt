package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.PopularMovieRepository
import com.irmak.themoviedc.ui.Fragment.PopularFragmentDirections
import com.irmak.themoviedc.viewModel.PopularMovieViewModel

@Suppress("UNCHECKED_CAST")
class PopularMovieViewModelFactory(private val repository: PopularMovieRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularMovieViewModel::class.java)){
            return PopularMovieViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("wrong view model")
    }
}