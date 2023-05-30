package com.irmak.themoviedc.model.popularModel

data class PopularMovieDetailResponse(
    val adult:Boolean? = null,
    val backdrop_path:String? = null,
    val budget:Int? = null,
//    val genres: List<Genres>? = null,
    val homepage:String? = null,
    val id:Int? = null,
    val imdb_id:String? = null,
    val original_language:String? = null,
    val original_title:String? = null,
    var overview:String? = null,
    val popularity:Number? = null,
    val poster_path:String? = null,
    val release_date:String? = null,
    val runtime:Int? = null,
    val tagline:String? = null,
    var title:String? = null,
    val video:Boolean? = null,
    val vote_average:Number? = null,
    val vote_count:Int?=null
)