package com.irmak.themoviedc.model.nowPlayingModel

data class NowPlayingModel(
//    val dates: DateNP? = null,
    val page: Int? = null,
    val results: List<com.irmak.themoviedc.model.nowPlayingModel.ResultNP>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)