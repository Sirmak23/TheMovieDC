package com.irmak.themoviedc.model.popularModel

data class PopularMovieResponse(
    val page:Int? = null,
    val results: List<com.irmak.themoviedc.model.popularModel.MovieRespons>?=null,
    val total_pages:Int?=null,
    val total_results:Int? = null
)
