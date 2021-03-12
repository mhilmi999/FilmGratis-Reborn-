package com.hilmi.filmgratis.model

import com.google.gson.annotations.SerializedName

class Trailer(
    @field:SerializedName("key") var key: String, @field:SerializedName(
        "name"
    ) var name: String
)