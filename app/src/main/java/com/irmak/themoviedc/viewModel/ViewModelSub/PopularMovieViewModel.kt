package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse
import com.irmak.themoviedc.repository.PopularMovieRepository
import kotlinx.coroutines.launch

class PopularMovieViewModel(private val popularMovieRepository: PopularMovieRepository):ViewModel() {

    private var mutablePopularMovieDetailList:MutableLiveData<com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse?> = MutableLiveData()
    val popularMovieDetailList:LiveData<com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse?>
    get() = mutablePopularMovieDetailList

    fun getPopularMovieDetail(){
        viewModelScope.launch {
        mutablePopularMovieDetailList.value = popularMovieRepository.getPopularMovieDetail()
        }
    }
}