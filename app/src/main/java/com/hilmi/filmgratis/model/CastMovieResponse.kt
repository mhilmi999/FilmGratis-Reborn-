package com.hilmi.filmgratis.model


import com.google.gson.annotations.SerializedName

data class CastMovieResponse(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)