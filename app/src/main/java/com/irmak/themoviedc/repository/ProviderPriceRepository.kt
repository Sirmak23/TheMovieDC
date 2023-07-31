package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class ProviderPriceRepository(private val movieApi: MovieApi) {
    suspend fun getTvProvidersPrice() = movieApi.getTvProvidersPrice()
}