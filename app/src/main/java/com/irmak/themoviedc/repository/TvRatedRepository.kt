package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class TvRatedRepository(private val movieApi: MovieApi) {
    suspend fun getTvRated() = movieApi.getTvRated()
}