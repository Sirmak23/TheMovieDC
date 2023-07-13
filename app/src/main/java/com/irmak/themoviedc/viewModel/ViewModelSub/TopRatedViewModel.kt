package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.topRatedModel.TopRatedRespone
import com.irmak.themoviedc.repository.TopRatedRepository
import kotlinx.coroutines.launch

class TopRatedViewModel(private val topRatedRepository: TopRatedRepository):ViewModel() {
    private val mutableTopList:MutableLiveData<TopRatedRespone> = MutableLiveData()
    val topList:LiveData<TopRatedRespone>
    get() = mutableTopList

    fun getTopRatedMovie(){
        viewModelScope.launch {
            mutableTopList.value = topRatedRepository.getTopRatedMovie()
        }
    }
}