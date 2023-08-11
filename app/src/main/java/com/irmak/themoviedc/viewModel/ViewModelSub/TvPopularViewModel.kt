package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.data.remote.api.tvPopuplarPageNo
import com.irmak.themoviedc.model.tvPopularModel.TvPopularModel
import com.irmak.themoviedc.repository.TvPopularRepository
import kotlinx.coroutines.launch

class TvPopularViewModel(private val tvPopularRepository: TvPopularRepository) : ViewModel() {
    private val mutableTvList: MutableLiveData<TvPopularModel> = MutableLiveData()
    val tvList: LiveData<TvPopularModel>
        get() = mutableTvList

    fun getPopularTv() {
        viewModelScope.launch {
            val topRateListWithFilter = tvPopularRepository.getPopularTv().results?.filter {
                // true ise liseye ekler değilse filtreler yani jp içermiyorsa true döndür ve listeye ekle
                it.genre_ids?.contains(16) != true
                it.origin_country?.contains("JP") != true
                it.origin_country?.contains("IN") != true
                it.overview.isNullOrEmpty() != true

            }
            val orijinalTopRatedList = tvPopularRepository.getPopularTv()
            orijinalTopRatedList.results = topRateListWithFilter
            mutableTvList.value = orijinalTopRatedList
        }
    }


}