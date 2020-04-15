package com.kirkbushman.gfycat.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReactionTags(

    @Json(name = "cursor")
    val cursor: String,

    @Json(name = "tags")
    val tags: List<Tag>
)
