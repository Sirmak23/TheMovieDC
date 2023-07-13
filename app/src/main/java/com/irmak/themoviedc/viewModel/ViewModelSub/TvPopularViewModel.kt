package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.tvPopularModel.TvPopularModel
import com.irmak.themoviedc.repository.TvPopularRepository
import kotlinx.coroutines.launch

class TvPopularViewModel(private val tvPopularRepository: TvPopularRepository):ViewModel() {
    private val mutableTvList: MutableLiveData<TvPopularModel> = MutableLiveData()
    val tvList:LiveData<TvPopularModel>
    get() = mutableTvList

    fun getPopularTv(){
        viewModelScope.launch {
            mutableTvList.value = tvPopularRepository.getPopularTv()
        }
    }
}