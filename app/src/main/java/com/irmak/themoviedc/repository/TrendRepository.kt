package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class TrendRepository(private val movieApi: MovieApi) {

    suspend fun getTrendAll() = movieApi.getTrendAll()
}