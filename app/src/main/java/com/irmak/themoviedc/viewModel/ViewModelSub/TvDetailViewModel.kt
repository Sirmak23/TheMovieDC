package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.tvDetailModel.TvDetailModel
import com.irmak.themoviedc.repository.TvDetailRepository
import kotlinx.coroutines.launch

class TvDetailViewModel(private val tvDetailRepository: TvDetailRepository):ViewModel() {
    private val mutableTvDetailList:MutableLiveData<TvDetailModel> = MutableLiveData()
    val tvDetailList:LiveData<TvDetailModel>
    get() = mutableTvDetailList

    fun getTvDetail(){
        viewModelScope.launch {
            mutableTvDetailList.value = tvDetailRepository.getTvDetail()
        }
    }

}