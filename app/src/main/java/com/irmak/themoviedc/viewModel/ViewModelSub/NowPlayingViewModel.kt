package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.nowPlayingModel.NowPlayingModel
import com.irmak.themoviedc.repository.NowPlayingRepository
import kotlinx.coroutines.launch

class NowPlayingViewModel(private val nowPlayingRepository: NowPlayingRepository):ViewModel() {
    private val mutableNowPlayList:MutableLiveData<com.irmak.themoviedc.model.nowPlayingModel.NowPlayingModel> = MutableLiveData()
    val nowPlaylist:LiveData<com.irmak.themoviedc.model.nowPlayingModel.NowPlayingModel>
    get() = mutableNowPlayList

    fun getNowPlayMovie(){
        viewModelScope.launch {
            mutableNowPlayList.value = nowPlayingRepository.getNowPlayMovie()
        }
    }
}