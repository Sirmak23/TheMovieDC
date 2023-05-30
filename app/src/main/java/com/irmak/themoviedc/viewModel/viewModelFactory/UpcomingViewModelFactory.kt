package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.UpcomingRepository
import com.irmak.themoviedc.viewModel.UpcomingViewModel

@Suppress("UNCHECKED_CAST")
class UpcomingViewModelFactory(private val repository: UpcomingRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpcomingViewModel::class.java)){
            return UpcomingViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("wrong view model")
    }
}