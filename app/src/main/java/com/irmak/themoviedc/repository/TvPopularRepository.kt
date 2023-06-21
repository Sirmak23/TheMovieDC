package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class TvPopularRepository(private val movieApi: MovieApi) {
    suspend fun getPopularTv()= movieApi.getPopularTv()
}