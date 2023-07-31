package com.irmak.themoviedc.model.tvDetailModel

data class TvDetailModel(
    val adult: Boolean,
    val backdrop_path: String?,
    val created_by: List<com.irmak.themoviedc.model.tvDetailModel.Creator>,
    val first_air_date: String,
    val genres: List<com.irmak.themoviedc.model.tvDetailModel.Genre>,
    val homepage: String?,
    val id: Int,
    val last_air_date: String,
    val last_episode_to_air: com.irmak.themoviedc.model.tvDetailModel.Episode?,
    val name: String,
    val next_episode_to_air: com.irmak.themoviedc.model.tvDetailModel.Episode?,
    val networks: List<com.irmak.themoviedc.model.tvDetailModel.Network>,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<com.irmak.themoviedc.model.tvDetailModel.ProductionCompany>,
    val production_countries: List<com.irmak.themoviedc.model.tvDetailModel.ProductionCountry>,
    val seasons: List<com.irmak.themoviedc.model.tvDetailModel.Season>,
    val spoken_languages: List<com.irmak.themoviedc.model.tvDetailModel.SpokenLanguage>,
    val status: String,
    val tagline: String?,
    val type: String,
    val vote_average: Double,
    val vote_count: Int
)

data class Creator(
    val id: Int,
    val credit_id: String,
    val name: String,
    val gender: Int,
    val profile_path: String?
)

data class Genre(
    val id: Int,
    val name: String
)

data class Episode(
    val id: Int,
    val name: String,
    val overview: String,
    val vote_average: Double,
    val vote_count: Int,
    val air_date: String,
    val episode_number: Int,
    val production_code: String?,
    val runtime: Int?,
    val season_number: Int,
    val show_id: Int,
    val still_path: String?
)

data class Network(
    val id: Int,
    val logo_path: String?,
    val name: String,
    val origin_country: String
)

data class ProductionCompany(
    val id: Int,
    val logo_path: String?,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class Season(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val season_number: Int,
    val vote_average: Double
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)