package com.irmak.themoviedc.model.tvTopRatedModel

import com.irmak.themoviedc.model.tvPopularModel.TvPopularResponse

data class TvTopRatedModel(
    val page:Int? = null,
    val results: List<TvTopRatedResponse>?=null,
    val total_pages:Int?=null,
    val total_results:Int? = null
)