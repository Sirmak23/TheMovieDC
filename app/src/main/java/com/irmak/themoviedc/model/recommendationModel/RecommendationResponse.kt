package com.irmak.themoviedc.model.recommendationModel

data class RecommendationResponse(
    val page: Int? = null,
    val results: List<com.irmak.themoviedc.model.recommendationModel.RecomTvShow>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)

data class RecomTvShow(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val original_language: String? = null,
    val original_name: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val media_type: String? = null,
    val genre_ids: List<Int>? = null,
    val popularity: Double? = null,
    val first_air_date: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    val origin_country: List<String>? = null
)
