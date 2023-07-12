package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class RecommendationRepository(private val movieApi: MovieApi) {
    suspend fun getRecommendations() = movieApi.getRecommendations()
}