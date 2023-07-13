package com.irmak.themoviedc.viewModel.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irmak.themoviedc.repository.RecommendationRepository
import com.irmak.themoviedc.viewModel.ViewModelSub.RecommendationViewModel

@Suppress("UNCHECKED_CAST")
class RecommendationViewModelFactory(private val repository: RecommendationRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecommendationViewModel::class.java)){
            return RecommendationViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("wrong view model")
    }
}