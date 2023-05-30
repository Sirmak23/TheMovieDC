package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class PopularListRepository(private var movieApi: MovieApi) {

    suspend fun getPopularList() = movieApi.getPopularList()
}