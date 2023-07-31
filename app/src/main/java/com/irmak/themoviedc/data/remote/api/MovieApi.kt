package com.irmak.themoviedc.data.remote.api

import com.irmak.themoviedc.model.UpcomingModel.UpcomingModel
import com.irmak.themoviedc.model.actorModel.ActorList
import com.irmak.themoviedc.model.actorModel.ActorMovies
import com.irmak.themoviedc.model.nowPlayingModel.NowPlayingModel
import com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse
import com.irmak.themoviedc.model.popularModel.PopularMovieResponse
import com.irmak.themoviedc.model.recommendationModel.RecommendationResponse
import com.irmak.themoviedc.model.search.SearchModel
import com.irmak.themoviedc.model.seasonInfoModel.SeasonInfoResponse
import com.irmak.themoviedc.model.storyModel.StoryModel
import com.irmak.themoviedc.model.topRatedModel.TopRatedRespone
import com.irmak.themoviedc.model.trailer.TrailerResponse
import com.irmak.themoviedc.model.trailer.TvTrailerResponse
import com.irmak.themoviedc.model.trendAll.TrendModel
import com.irmak.themoviedc.model.tvActorModel.TvActorModel
import com.irmak.themoviedc.model.tvDetailModel.TvDetailModel
import com.irmak.themoviedc.model.tvPopularModel.TvPopularModel
import com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedModel
import com.irmak.themoviedc.model.watchProviders.MovieProviderDetails
import com.irmak.themoviedc.model.watchProviders.ProviderPriceResponse
import com.irmak.themoviedc.model.watchProviders.TvMovieProviderDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

var pageNumber: Int = 1
var seasonNo: Int = 1
var personId: Int? = 1
var movieIdNumber: Int? = 420888
var tvIdNumber: Int? = 71912
var searchWord: String? = ""
var timePeriod: String? = "day"
var tvPopuplarPageNo: Int = 1
var tvTopRatedPageNo: Int = 1
var pageNumberNpStory: Int = 1
var pageNumberTopRated: Int = 1
var objectType: String = ""
var idtype: String = "tmdb"
var localeInfo: String = "tr_TR"
var MovieOrTvID: Int = 266686

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
    ):PopularMovieDetailResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getActorDetail(
        @Path("movie_id") movieId: Int? = movieIdNumber,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr"
    ): ActorList

    @GET("tv/{series_id}/credits")
    suspend fun getTvActorDetail(
        @Path("series_id") tvId: Int? = tvIdNumber,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr"
    ): TvActorModel

    @GET("movie/{movie_id}/videos")
    suspend fun getVideo(
        @Path("movie_id") movieId: Int? = movieIdNumber,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr"
    ): TrailerResponse

    @GET("tv/{series_id}/videos")
    suspend fun getTvVideo(
        @Path("series_id") tvId: Int? = tvIdNumber,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr"
    ): TvTrailerResponse

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
        @Query("page") pageNumber: Int = pageNumberNpStory
    ): StoryModel

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
        @Query("page") pageNumber: Int = 1
    ): UpcomingModel

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
        @Query("page") pageNumber: Int = pageNumberTopRated
    ): TopRatedRespone

    @GET("search/multi")
    suspend fun getSearch(
        @Query("query") word: String? = searchWord,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
        @Query("page") pageNumber: Int = 1
    ): SearchModel

    @GET("tv/popular")
    suspend fun getPopularTv(
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
        @Query("page") pageNumber: Int = tvPopuplarPageNo
    ): TvPopularModel

    @GET("tv/top_rated")
    suspend fun getTvRated(
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
        @Query("page") pageNumber: Int = tvTopRatedPageNo
    ): TvTopRatedModel

    @GET("tv/{series_id}")
    suspend fun getTvDetail(
        @Path("series_id") tvId: Int? = tvIdNumber,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
    ): TvDetailModel

    @GET("tv/{series_id}/season/{season_number}")
    suspend fun getSeasonDetail(
        @Path("series_id") tvId: Int? = tvIdNumber,
        @Path("season_number") seasonNumber: Int? = seasonNo,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
    ): SeasonInfoResponse

    @GET("tv/{series_id}/recommendations")
    suspend fun getRecommendations(
        @Path("series_id") tvId: Int? = tvIdNumber,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
    ): RecommendationResponse

    @GET("person/{person_id}/movie_credits")
    suspend fun getActorMovies(
        @Path("person_id") persId: Int? = personId,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
    ): ActorMovies

    @GET("trending/all/{time_window}")
    suspend fun getTrendAll(
        @Path("time_window") timePd: String? = timePeriod,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
    ):TrendModel

    @GET("movie/{movie_id}/watch/providers")
    suspend fun getProviders(
        @Path("movie_id") moviId: Int? = movieIdNumber,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
    ): MovieProviderDetails

    @GET("tv/{series_id}/watch/providers")
    suspend fun getTvProviders(
        @Path("series_id") serId: Int? = tvIdNumber,
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
    ):TvMovieProviderDetails

    @GET("https://apis.justwatch.com/contentpartner/v2/content/offers/object_type/{object_type}/id_type/{id_type}/locale/{locale}")
    suspend fun getTvProvidersPrice(
        @Path("object_type") objType: String? = objectType,
        @Path("id_type") idType: String? = idtype,
        @Path("locale") locale: String? = localeInfo,
        @Query("id") id: Int? = MovieOrTvID,
        @Query("token") token: String = "ABCdef12",
        ): ProviderPriceResponse
}