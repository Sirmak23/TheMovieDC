package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi

class ActorSearchRepository(val movieApi: MovieApi) {

    suspend fun getActorSearch ()= movieApi.getActorSearch()
}