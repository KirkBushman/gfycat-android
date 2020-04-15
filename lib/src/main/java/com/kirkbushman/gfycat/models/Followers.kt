package com.kirkbushman.gfycat.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Followers(

    @Json(name = "status")
    val status: String,

    @Json(name = "followers")
    val followers: List<UserRef>,

    @Json(name = "cursor")
    val cursor: String,

    @Json(name = "count")
    val count: Int,

    @Json(name = "totalCount")
    val totalCount: Int
)
