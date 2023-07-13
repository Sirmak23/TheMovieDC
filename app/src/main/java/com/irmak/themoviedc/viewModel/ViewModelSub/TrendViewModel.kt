package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.trailer.TrailerResponse
import com.irmak.themoviedc.model.trendAll.TrendModel
import com.irmak.themoviedc.repository.TrailerRepository
import com.irmak.themoviedc.repository.TrendRepository
import kotlinx.coroutines.launch

class TrendViewModel (private val trendRepository: TrendRepository): ViewModel() {
    private var mutableTrendList: MutableLiveData<TrendModel?> = MutableLiveData()
    val trendList: LiveData<TrendModel?>
        get() = mutableTrendList

    fun getTrendAll(){
        viewModelScope.launch {
            mutableTrendList.value = trendRepository.getTrendAll()
        }
    }

}