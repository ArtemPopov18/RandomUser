package com.example.randomuser.data.model

import com.google.gson.annotations.SerializedName

data class PageResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
)