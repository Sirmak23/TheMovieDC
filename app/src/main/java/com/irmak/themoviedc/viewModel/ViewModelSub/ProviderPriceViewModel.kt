package com.irmak.themoviedc.viewModel.ViewModelSub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.themoviedc.model.watchProviders.ProviderPriceResponse
import com.irmak.themoviedc.repository.ProviderPriceRepository
import kotlinx.coroutines.launch

class ProviderPriceViewModel(private val providerPriceRepository: ProviderPriceRepository):ViewModel() {
    private var mutableProvidePriceList: MutableLiveData<ProviderPriceResponse?> = MutableLiveData()
    val providePriceList: LiveData<ProviderPriceResponse?>
        get() = mutableProvidePriceList

    fun getTvProvidersPrice(){
        viewModelScope.launch {
            mutableProvidePriceList.value = providerPriceRepository.getTvProvidersPrice()
        }
    }
}