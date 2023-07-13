package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class TvWatchProviderRepository(private val movieApi: MovieApi) {
    suspend fun getTvProviders() = movieApi.getTvProviders()
}