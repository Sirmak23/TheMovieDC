package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class NowPlayingRepository(private val movieApi: MovieApi) {

    suspend fun getNowPlayMovie() = movieApi.getNowPlayMovie()
}