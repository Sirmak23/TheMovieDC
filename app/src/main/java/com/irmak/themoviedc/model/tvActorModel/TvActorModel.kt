package com.irmak.themoviedc.model.tvActorModel


data class TvActorModel(
    val cast: List<com.irmak.themoviedc.model.tvActorModel.CastResponse>,
    val crew: List<com.irmak.themoviedc.model.tvActorModel.CrewResponse>,
    val id: Int
)


data class CastResponse(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?,
    val character: String,
    val credit_id: String,
    val order: Int
)

data class CrewResponse(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?,
    val credit_id: String,
    val department: String,
    val job: String
)


