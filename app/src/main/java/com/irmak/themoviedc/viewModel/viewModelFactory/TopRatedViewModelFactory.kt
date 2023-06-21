package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.PopularListRepository
import com.irmak.themoviedc.repository.TopRatedRepository
import com.irmak.themoviedc.viewModel.MovieViewModel
import com.irmak.themoviedc.viewModel.TopRatedViewModel


@Suppress("UNCHECKED_CAST")
class TopRatedViewModelFactory(private val repository: TopRatedRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopRatedViewModel::class.java)) {
            return TopRatedViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Wrong View Model")
    }
}
