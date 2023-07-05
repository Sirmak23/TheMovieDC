package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class TvActorRepository(private val movieApi: MovieApi) {

    suspend fun getTvActorDetail()=movieApi.getTvActorDetail()
}