package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.actorModel.ActorList
import com.irmak.themoviedc.model.tvActorModel.TvActorModel
import com.irmak.themoviedc.repository.ActorRepository
import com.irmak.themoviedc.repository.TvActorRepository
import kotlinx.coroutines.launch

class TvActorViewModel (private var tvActorRepository: TvActorRepository): ViewModel() {
    private var mutableTvActorList: MutableLiveData<TvActorModel?> = MutableLiveData()
    val tvActorList: LiveData<TvActorModel?>
        get() = mutableTvActorList

    fun getTvActorDetail(){
        viewModelScope.launch {
            mutableTvActorList.value = tvActorRepository.getTvActorDetail()
        }
    }
}