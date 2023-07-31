package com.irmak.themoviedc.model.trailer

data class TvTrailerResponse(
    val id: Int,
    val results: List<com.irmak.themoviedc.model.trailer.TvTrailerKey>
)

