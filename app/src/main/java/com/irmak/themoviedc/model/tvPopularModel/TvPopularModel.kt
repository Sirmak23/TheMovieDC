package com.irmak.themoviedc.model.tvPopularModel

data class TvPopularModel(
    val page: Int? = null,
    var results: List<TvPopularResponse>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)
