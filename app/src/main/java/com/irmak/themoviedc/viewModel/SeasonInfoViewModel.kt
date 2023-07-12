package com.irmak.themoviedc.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.actorModel.ActorList
import com.irmak.themoviedc.model.seasonInfoModel.SeasonInfoResponse

import com.irmak.themoviedc.repository.SeasonInfoRepository
import kotlinx.coroutines.launch

class SeasonInfoViewModel(private val seasonInfoRepository: SeasonInfoRepository):ViewModel() {
    private var mutableSeasonInfoList: MutableLiveData<SeasonInfoResponse?> = MutableLiveData()
    val seasonInfoList: LiveData<SeasonInfoResponse?>
        get() = mutableSeasonInfoList

    fun getSeasonDetail(){
        viewModelScope.launch {
            mutableSeasonInfoList.value = seasonInfoRepository.getSeasonDetail()
        }
    }
}