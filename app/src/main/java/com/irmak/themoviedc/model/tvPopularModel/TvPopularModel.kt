package com.irmak.themoviedc.model.tvPopularModel
import com.irmak.themoviedc.model.popularModel.MovieRespons

data class TvPopularModel(
    val page:Int? = null,
    val results: List<TvPopularResponse>?=null,
    val total_pages:Int?=null,
    val total_results:Int? = null
)
