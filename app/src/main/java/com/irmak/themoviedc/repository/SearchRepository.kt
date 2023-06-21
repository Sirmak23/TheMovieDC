package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class SearchRepository(private val movieApi: MovieApi) {
    suspend fun getSearch() = movieApi.getSearch()
}