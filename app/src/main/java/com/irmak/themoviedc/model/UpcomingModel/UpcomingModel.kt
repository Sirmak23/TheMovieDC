package com.irmak.themoviedc.model.UpcomingModel


data class UpcomingModel(
    val dates: com.irmak.themoviedc.model.UpcomingModel.DateUP? = null,
    val page: Int? = null,
    val results: List<com.irmak.themoviedc.model.UpcomingModel.ResultUP>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)