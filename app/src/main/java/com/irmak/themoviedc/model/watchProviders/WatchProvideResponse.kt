package com.irmak.themoviedc.model.watchProviders

data class WatchProvideResponse(
    val link: String? = null,
    val buy: List<Provider>? = null,
    val flatrate: List<Provider>? = null,
    val rent: List<Provider>? = null
)

data class Provider(
    val logo_path: String? = null,
    val provider_id: Int? = null,
    val provider_name: String? = null,
    val display_priority: Int? = null,
    var retail_price: Double? = null

)

