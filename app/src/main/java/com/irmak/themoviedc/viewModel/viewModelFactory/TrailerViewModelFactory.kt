package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.TrailerRepository
import com.irmak.themoviedc.ui.Fragment.PopularFragmentDirections
import com.irmak.themoviedc.viewModel.TrailerViewModel

@Suppress("UNCHECKED_CAST")
class TrailerViewModelFactory(private val repository: TrailerRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrailerViewModel::class.java)){
            return TrailerViewModel(repository) as T
        }
        throw IllegalArgumentException("wrong view model")
    }
}