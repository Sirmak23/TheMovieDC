package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.search.SearchModel
import com.irmak.themoviedc.repository.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository):ViewModel() {
    private val mutableSearchList:MutableLiveData<com.irmak.themoviedc.model.search.SearchModel> = MutableLiveData()
    val searchList:LiveData<com.irmak.themoviedc.model.search.SearchModel>
    get() = mutableSearchList

    fun getSearch(){
        viewModelScope.launch {
            mutableSearchList.value = searchRepository.getSearch()
        }
    }
}