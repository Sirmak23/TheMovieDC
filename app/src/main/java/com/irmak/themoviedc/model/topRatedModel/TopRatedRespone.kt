package com.irmak.themoviedc.model.topRatedModel

import com.irmak.themoviedc.model.popularModel.MovieRespons

data class TopRatedRespone(
    val page:Int? = null,
    val results: List<com.irmak.themoviedc.model.topRatedModel.topRatedResult>?=null,
    val total_pages:Int?=null,
    val total_results:Int? = null


)
