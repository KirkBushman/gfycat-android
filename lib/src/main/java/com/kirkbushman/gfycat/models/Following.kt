package com.kirkbushman.gfycat.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Following(

    @Json(name = "status")
    val status: String,

    @Json(name = "follows")
    val follows: List<UserRef>,

    @Json(name = "cursor")
    val cursor: String,

    @Json(name = "count")
    val count: Int,

    @Json(name = "totalCount")
    val totalCount: Int
)
