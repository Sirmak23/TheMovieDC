package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.data.remote.api.tvTopRatedPageNo
import com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedModel
import com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedResponse
import com.irmak.themoviedc.repository.TvRatedRepository
import com.irmak.themoviedc.ui.extensions.isBackOrNext
import kotlinx.coroutines.launch


//class TvRatedViewModel(private val tvRatedRepository: TvRatedRepository):ViewModel() {
//    private val mutableTvRatedList: MutableLiveData<TvTopRatedModel> = MutableLiveData()
//    val TvRatedList:LiveData<TvTopRatedModel>
//    get() = mutableTvRatedList
//
//    fun getTvRated(){
//        viewModelScope.launch {
//            val topRateListWithFilter = tvRatedRepository.getTvRated().results?.filter {
//                it.origin_country?.contains("JP") != true
//                it.origin_country?.contains("IN") != true
//            }
//            val orijinalTopRatedList = tvRatedRepository.getTvRated()
//            orijinalTopRatedList.results = topRateListWithFilter
//            mutableTvRatedList.value = orijinalTopRatedList
//
//        }
//    }
//}


class TvRatedViewModel(private val tvRatedRepository: TvRatedRepository) : ViewModel() {
    private val mutableTvRatedList: MutableLiveData<TvTopRatedModel> = MutableLiveData()
    val tvRatedList: LiveData<TvTopRatedModel>
        get() = mutableTvRatedList

    //    fun getTvRated() {
//        viewModelScope.launch {
//            repeat(3) { // 3 sayfa çekmek için tekrar ediyoruz (3 yerine istediğiniz sayıyı yazabilirsiniz)
//                val page = it + 1
//                val topRatedListWithFilter = tvRatedRepository.getTvRated().results?.filter {
//                    it.overview.isNullOrEmpty() != true &&
//                            it.origin_country?.contains("JP") != true &&
//                            it.origin_country?.contains("IN") != true &&
//                            it.genre_ids?.contains(16) != true
//                }
//
//                val originalTvTopRatedList = tvRatedRepository.getTvRated()
//                originalTvTopRatedList.results = topRatedListWithFilter
//                mutableTvRatedList.value = originalTvTopRatedList
//            }
//        }
//
//    }
//}
    fun getTvRated() {
        viewModelScope.launch {
            val topRatedListWithFilter = mutableListOf<TvTopRatedResponse>()

            repeat(3) {
                if (isBackOrNext ==1 ){
                    tvTopRatedPageNo++
                } else if (isBackOrNext ==2){
                    tvTopRatedPageNo--
                }else if (isBackOrNext == 0){
                    tvTopRatedPageNo++
                }

                val response = tvRatedRepository.getTvRated()

                response.results?.let { items ->
                    topRatedListWithFilter.addAll(items.filter {
                        it.overview.isNullOrEmpty() != true &&
                                it.origin_country?.contains("JP") != true &&
                                it.origin_country?.contains("IN") != true &&
                                it.genre_ids?.contains(16) != true
                    })
                }
            }

            val originalTvTopRatedList = tvRatedRepository.getTvRated()
            originalTvTopRatedList.results = topRatedListWithFilter
            mutableTvRatedList.value = originalTvTopRatedList
        }
    }
}


