package com.irmak.themoviedc.model.search

data class SearchResponse(
    val poster_path: String? = null ,
    val backdrop_path: String? = null ,
    val adult: Boolean?= null,
    val overview: String?= null,
    val release_date: String? = null,
    val genre_ids: List<Int>? = null,
    val id: Int? = null,
    val original_title: String? = null,
    val original_language: String? = null,
    val title: String? = null,
    val media_type:String?=null,
    val popularity: Number? = null,
    val vote_count: Int? = null,
    val video: Boolean? = null,
    val vote_average: Number? = null,
)
