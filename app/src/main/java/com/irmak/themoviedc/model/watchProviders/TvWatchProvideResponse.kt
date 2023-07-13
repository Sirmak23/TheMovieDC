package com.irmak.themoviedc.model.watchProviders

data class TvWatchProvideResponse(
    val link: String? = null,
    val flatrate: List<TvProvider>? = null,
)
data class TvProvider(
    val logo_path: String? = null,
    val provider_id: Int? = null,
    val provider_name: String? = null,
    val display_priority: Int? = null

)