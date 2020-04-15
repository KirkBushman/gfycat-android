package com.kirkbushman.gfycat.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tag(

    @Json(name = "tag")
    val tag: String,

    @Json(name = "tagText")
    val tagText: String,

    @Json(name = "gfycats")
    val gfycats: List<Gfycat>,

    @Json(name = "cursor")
    val cursor: String,

    @Json(name = "digest")
    val digest: String
)
