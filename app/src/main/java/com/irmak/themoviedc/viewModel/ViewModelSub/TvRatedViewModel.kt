package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedModel
import com.irmak.themoviedc.repository.TvRatedRepository
import kotlinx.coroutines.launch

class TvRatedViewModel(private val tvRatedRepository: TvRatedRepository):ViewModel() {
    private val mutableTvRatedList: MutableLiveData<TvTopRatedModel> = MutableLiveData()
    val TvRatedList:LiveData<TvTopRatedModel>
    get() = mutableTvRatedList

    fun getTvRated(){
        viewModelScope.launch {
            mutableTvRatedList.value = tvRatedRepository.getTvRated()
        }
    }
}