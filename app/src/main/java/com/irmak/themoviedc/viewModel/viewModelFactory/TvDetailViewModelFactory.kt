package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.TvDetailRepository
import com.irmak.themoviedc.viewModel.ViewModelSub.TvDetailViewModel

@Suppress("UNCHECKED_CAST")
class TvDetailViewModelFactory(private val repository: TvDetailRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvDetailViewModel::class.java)) {
            return TvDetailViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Wrong View Model")
    }
}