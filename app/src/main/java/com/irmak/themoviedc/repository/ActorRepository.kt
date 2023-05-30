package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class ActorRepository(private val movieApi: MovieApi) {

    suspend fun getActorDetail() = movieApi.getActorDetail()
}