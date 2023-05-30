package com.irmak.themoviedc.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.actorModel.ActorList
import com.irmak.themoviedc.repository.ActorRepository
import kotlinx.coroutines.launch

class ActorViewModel(private var actorRepository: ActorRepository):ViewModel() {
private var mutableActorList:MutableLiveData<ActorList?> = MutableLiveData()
    val actorList:LiveData<ActorList?>
    get() = mutableActorList

fun getActorDetail(){
    viewModelScope.launch {
        mutableActorList.value = actorRepository.getActorDetail()
    }
}
}