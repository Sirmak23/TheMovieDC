package com.irmak.themoviedc.model.seasonInfoModel

data class SeasonInfoResponse(
    val air_date: String? = null,
    val episodes: List<com.irmak.themoviedc.model.seasonInfoModel.EpisodeData>? = null
)

data class EpisodeData(
    val air_date: String? = null,
    val episode_number: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val overview: String? = null,
    val production_code: String? = null,
    val runtime: Int? = null,
    val season_number: Int? = null,
    val show_id: Int? = null,
    val still_path: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    val crew: List<com.irmak.themoviedc.model.seasonInfoModel.CrewData>? = null
)

data class CrewData(
    val job: String? = null,
    val department: String? = null,
    val credit_id: String? = null,
    val adult: Boolean? = null,
    val gender: Int? = null,
    val id: Int? = null,
    val known_for_department: String? = null,
    val name: String? = null,
    val original_name: String? = null,
    val popularity: Double? = null,
    val profile_path: String? = null
)

