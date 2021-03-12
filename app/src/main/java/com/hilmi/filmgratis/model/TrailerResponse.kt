package com.hilmi.filmgratis.model

import com.google.gson.annotations.SerializedName

class TrailerResponse {
    @SerializedName("id")
    var id_trailer = 0

    @SerializedName("results")
    val results: List<Trailer>? = null
}