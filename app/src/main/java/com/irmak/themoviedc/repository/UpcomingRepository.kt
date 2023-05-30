package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class UpcomingRepository(private val movieApi: MovieApi) {

    suspend fun getUpcomingMovie() = movieApi.getUpcomingMovie()
}