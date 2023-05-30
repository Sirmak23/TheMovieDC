package com.irmak.themoviedc.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.popularModel.MovieRespons
import com.irmak.themoviedc.model.popularModel.PopularMovieResponse
import com.irmak.themoviedc.repository.PopularListRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val popularMovieListRepository:PopularListRepository):ViewModel() {
    private var mutableMovieList:MutableLiveData<PopularMovieResponse?> = MutableLiveData()
    val movieList:LiveData<PopularMovieResponse?>
    get() = mutableMovieList

    fun getPopularList(){
        viewModelScope.launch {
            mutableMovieList.value = popularMovieListRepository.getPopularList()
        }
    }
}