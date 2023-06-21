package com.irmak.themoviedc.model.tvPopularModel

data class TvPopularResponse(
    val poster_path: String? = null ,
    val backdrop_path: String? = null ,
    val adult: Boolean?= null,
    val overview: String?= null,
    val first_air_date: String? = null,
    val genre_ids: List<Int>? = null,
    val id: Int? = null,
    val name: String? = null,
    val original_name: String? = null,
    val title: String? = null,
    val popularity: Number? = null,
    val vote_count: Int? = null,
    val video: Boolean? = null,
    val vote_average: Number? = null,
)
