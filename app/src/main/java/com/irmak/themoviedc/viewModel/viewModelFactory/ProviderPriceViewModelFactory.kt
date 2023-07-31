package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.PopularListRepository
import com.irmak.themoviedc.repository.ProviderPriceRepository
import com.irmak.themoviedc.viewModel.ViewModelSub.MovieViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.ProviderPriceViewModel

class ProviderPriceViewModelFactory (private val repository: ProviderPriceRepository):
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProviderPriceViewModel::class.java)){
            return ProviderPriceViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Wrong View Model")
    }
}