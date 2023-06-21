package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class TopRatedRepository(private val movieApi: MovieApi) {
    suspend fun getTopRatedMovie() = movieApi.getTopRatedMovie()
}