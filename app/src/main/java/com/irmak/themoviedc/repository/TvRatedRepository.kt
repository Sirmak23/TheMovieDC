package com.irmak.themoviedc.repository

import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedModel
import com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedResponse
import retrofit2.Response
import java.net.SocketTimeoutException

class TvRatedRepository(private val movieApi: MovieApi) {
    suspend fun getTvRated() = movieApi.getTvRated()

}