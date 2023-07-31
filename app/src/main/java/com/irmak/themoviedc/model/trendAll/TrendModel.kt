package com.irmak.themoviedc.model.trendAll


data class TrendModel(
    val page:Int? = null,
    val results: List<com.irmak.themoviedc.model.trendAll.TrendResponse>?=null,
    val total_pages:Int?=null,
    val total_results:Int? = null

)

data class TrendResponse(
    val adult: Boolean?=null,
    val backdrop_path: String?=null,
    val id: Int?=null,
    val title: String?=null,
    val name: String?=null,
    val original_language: String?=null,
    val original_title: String?=null,
    val overview: String?=null,
    val poster_path: String?=null,
    val media_type: String?=null,
    val genre_ids: List<Int>?=null,
    val popularity: Double?=null,
    val release_date: String?=null,
    val video: Boolean?=null,
    val vote_average: Double?=null,
    val vote_count: Int?=null
)
