package com.irmak.themoviedc.model.search


data class SearchModel(
    val page: Int? = null,
    val results: List<SearchResponse>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)
