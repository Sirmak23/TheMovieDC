package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.SearchRepository
import com.irmak.themoviedc.repository.SeasonInfoRepository
import com.irmak.themoviedc.viewModel.SearchViewModel
import com.irmak.themoviedc.viewModel.SeasonInfoViewModel

@Suppress("UNCHECKED_CAST")
class SeasonInfoViewModelFactory(private val repository: SeasonInfoRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SeasonInfoViewModel::class.java)){
            return SeasonInfoViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("wrong view model")
    }
}