package com.irmak.themoviedc.model.UpcomingModel


data class UpcomingModel(
    val dates: DateUP? = null,
    val page: Int? = null,
    val results: List<ResultUP>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)