package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class TvDetailRepository(private val  movieApi: MovieApi) {
    suspend fun getTvDetail() = movieApi.getTvDetail()
}