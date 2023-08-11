package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.actorModel.ActorList
import com.irmak.themoviedc.model.search.ActorSearchModel
import com.irmak.themoviedc.repository.ActorSearchRepository
import kotlinx.coroutines.launch

class ActorSearchViewModel(private var actorSearchRepository: ActorSearchRepository): ViewModel() {
    private var mutableActorSearchList: MutableLiveData<ActorSearchModel> = MutableLiveData()
    val actorSearchList: LiveData<ActorSearchModel?>
        get() = mutableActorSearchList

    fun getActorDetail(){
        viewModelScope.launch {
            mutableActorSearchList.value = actorSearchRepository.getActorSearch()

        }
    }
}