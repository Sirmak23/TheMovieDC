package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.UpcomingModel.UpcomingModel
import com.irmak.themoviedc.repository.UpcomingRepository
import kotlinx.coroutines.launch

class UpcomingViewModel(private val upcomingRepository:UpcomingRepository):ViewModel(){
    private val  mutableUpcomingList:MutableLiveData<com.irmak.themoviedc.model.UpcomingModel.UpcomingModel> = MutableLiveData()
    val upcomingList:LiveData<com.irmak.themoviedc.model.UpcomingModel.UpcomingModel>
    get() = mutableUpcomingList

    fun getUpcomingMovie(){
        viewModelScope.launch {
            mutableUpcomingList.value = upcomingRepository.getUpcomingMovie()
        }
    }
}