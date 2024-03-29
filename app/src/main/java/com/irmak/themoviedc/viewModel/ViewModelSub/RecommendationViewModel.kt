package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse
import com.irmak.themoviedc.model.recommendationModel.RecommendationResponse
import com.irmak.themoviedc.repository.PopularMovieRepository
import com.irmak.themoviedc.repository.RecommendationRepository
import kotlinx.coroutines.launch

class RecommendationViewModel (private val recommendationRepository: RecommendationRepository):
    ViewModel() {
    private var mutableRecomList: MutableLiveData<com.irmak.themoviedc.model.recommendationModel.RecommendationResponse?> = MutableLiveData()
    val recomList: LiveData<com.irmak.themoviedc.model.recommendationModel.RecommendationResponse?>
        get() = mutableRecomList

    fun getRecommendations(){
        viewModelScope.launch {
            mutableRecomList.value = recommendationRepository.getRecommendations()
        }
    }
}