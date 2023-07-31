package com.irmak.themoviedc.model.trailer

data class TrailerResponse(
    val id:Int?=null,
    val results:List<com.irmak.themoviedc.model.trailer.TrailerKey>?=null
)
