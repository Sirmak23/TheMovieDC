package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class PopularMovieRepository(private var movieApi: MovieApi) {

    suspend fun getPopularMovieDetail() = movieApi.getPopularMovieDetail()
}