package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class TrailerRepository(private var movieApi: MovieApi) {

    suspend fun getVideo() = movieApi.getVideo()
}