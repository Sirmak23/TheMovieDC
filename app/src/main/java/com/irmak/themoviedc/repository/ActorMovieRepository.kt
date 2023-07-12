package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class ActorMovieRepository(private val movieApi: MovieApi) {
    suspend fun getActorMovies() = movieApi.getActorMovies()
}