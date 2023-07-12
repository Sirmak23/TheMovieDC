package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class SeasonInfoRepository(private val movieApi: MovieApi) {

    suspend fun getSeasonDetail()= movieApi.getSeasonDetail()
}