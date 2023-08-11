package com.irmak.themoviedc.model.search

data class ActorSearchModel(
        val page: Int,
        val results: List<Person>
)

data class Person(
        val adult: Boolean,
        val gender: Int,
        val id: Int,
        val known_for_department: String,
        val name: String,
        val original_name: String,
        val popularity: Double,
        val profile_path: String?,
        val known_for: List<KnownFor>
)

data class KnownFor(
        val adult: Boolean,
        val backdrop_path: String?,
        val id: Int,
        val title: String,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val poster_path: String?,
        val media_type: String,
        val genre_ids: List<Int>,
        val popularity: Double,
        val release_date: String,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int
)


