package com.irmak.themoviedc.model.watchProviders

data class TvMovieProviderDetails (
    val id: Int,
    val results: TvResults
)

data class TvResults(
    val TR:TvWatchProvideResponse?=null
)

