package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.actorModel.ActorMovies
import com.irmak.themoviedc.repository.ActorMovieRepository
import kotlinx.coroutines.launch

class ActorMovieViewModel (private var actorMovieRepository: ActorMovieRepository): ViewModel() {
    private var mutableActorMovieList: MutableLiveData<com.irmak.themoviedc.model.actorModel.ActorMovies?> = MutableLiveData()
    val actorMovieList: LiveData<com.irmak.themoviedc.model.actorModel.ActorMovies?>
        get() = mutableActorMovieList

    fun getActorMovies(){
        viewModelScope.launch {
            mutableActorMovieList.value = actorMovieRepository.getActorMovies()
        }
    }
}