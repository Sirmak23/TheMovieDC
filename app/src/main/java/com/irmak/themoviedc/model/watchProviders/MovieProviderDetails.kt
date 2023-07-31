package com.irmak.themoviedc.model.watchProviders

data class MovieProviderDetails(
    val id: Int? = null,
    val results:Results? = null,
)

data class Results(
    val TR: WatchProvideResponse? = null
)

