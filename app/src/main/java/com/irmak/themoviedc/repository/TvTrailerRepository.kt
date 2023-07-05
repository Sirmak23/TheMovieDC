package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class TvTrailerRepository(private val movieApi: MovieApi) {
    suspend fun getTvVideo() = movieApi.getTvVideo()
}