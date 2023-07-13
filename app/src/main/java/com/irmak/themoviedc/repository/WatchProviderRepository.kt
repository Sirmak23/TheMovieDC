package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class WatchProviderRepository(private val movieApi: MovieApi) {
    suspend fun getProviders() = movieApi.getProviders()
}