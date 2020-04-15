package com.kirkbushman.gfycat.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRef(

    @Json(name = "user_id")
    val userId: String,

    @Json(name = "follower_id")
    val followerId: String,

    @Json(name = "follow_date")
    val followerDate: Long
)
