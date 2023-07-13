package com.irmak.themoviedc.model.watchProviders

data class MovieProviderDetails(
    val id: Int,
    val results: Results
)

data class Results(
    val TR: WatchProvideResponse?=null
)

