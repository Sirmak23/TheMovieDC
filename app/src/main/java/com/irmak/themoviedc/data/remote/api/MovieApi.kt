package com.irmak.themoviedc.data.remote.api

import com.irmak.themoviedc.model.UpcomingModel.UpcomingModel
import com.irmak.themoviedc.model.actorModel.ActorList
import com.irmak.themoviedc.model.nowPlayingModel.NowPlayingModel
import com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse
import com.irmak.themoviedc.model.popularModel.PopularMovieResponse
import com.irmak.themoviedc.model.storyModel.StoryModel
import com.irmak.themoviedc.model.trailer.TrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

var pageNumber: Int = 1
var movieIdNumber: Int? = 420888

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularList(
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
        @Query("page") pageNo: Int = pageNumber
    ): PopularMovieResponse

    @GET("movie/{movie_id}")
    suspend fun getPopularMovieDetail(
        @Path("movie_id") movieId: Int? = movieIdNumber,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr"
    ): PopularMovieDetailResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getActorDetail(
        @Path("movie_id") movieId: Int? = movieIdNumber,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr"
    ): ActorList

    @GET("movie/{movie_id}/videos")
    suspend fun getVideo(
        @Path("movie_id") movieId: Int? = movieIdNumber,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr"
    ): TrailerResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayMovie(
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
        @Query("page") pageNumber: Int = 1
    ): NowPlayingModel
    @GET("movie/now_playing")
    suspend fun getPlayVideoMovie(
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
        @Query("page") pageNumber: Int = 1
    ): StoryModel

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
        @Query("page") pageNumber: Int = 1
    ): UpcomingModel

}
