package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class StoryRepository(private val movieApi: MovieApi) {
    suspend fun getPlayVideoMovie() = movieApi.getPlayVideoMovie()
}