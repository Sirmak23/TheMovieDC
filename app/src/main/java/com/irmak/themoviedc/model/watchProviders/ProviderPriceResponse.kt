package com.irmak.themoviedc.model.watchProviders

data class ProviderPriceResponse(
    val offers: List<com.irmak.themoviedc.model.watchProviders.Offer>,
    val title: String? = null,
    val original_title: String? = null,
    val original_release_year: Int? = null,
    val director: String? = null,
    val full_path: String? = null,
    val justwatch_id: Int? = null,
    val imdb_id: String? = null,
    val tmdb_id: String? = null,
    val production_countries: List<String>? = null,
    val original_language: String? = null,
    val runtime: Int? = null,
    val object_type: String? = null,
    val genre_ids: List<Int>? = null,

    )

data class Offer(
    val monetization_type: String? = null,
    val provider_id: Int? = null,
    val presentation_type: String? = null,
    val date_created: String? = null,
    val retail_price: Double? = null,
    val currency: String? = null,
    val urls: com.irmak.themoviedc.model.watchProviders.Urls
)

data class Urls(
    val standard_web: String? = null,
    val raw_web: String? = null,
    val deeplink_android_tv: String? = null,
    val deeplink_tvos: String? = null
)

