package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.storyModel.StoryModel
import com.irmak.themoviedc.repository.StoryRepository
import kotlinx.coroutines.launch


class StoryViewModel(private val storyRepository: StoryRepository): ViewModel() {
    private val mutableStoryPlayList: MutableLiveData<StoryModel> = MutableLiveData()
    val nowPlayVideolist: LiveData<StoryModel>
        get() = mutableStoryPlayList

    fun getPlayVidoeMovie(){
        viewModelScope.launch {
            mutableStoryPlayList.value = storyRepository.getPlayVideoMovie()
        }
    }
}
