package com.irmak.themoviedc.model.tvTopRatedModel

data class TvTopRatedModel(
    val page: Int? = null,
    var results: List<TvTopRatedResponse>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)