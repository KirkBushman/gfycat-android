package com.kirkbushman.gfycat.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class GfycatUrl(

    @Json(name = "url")
    val url: String?,

    @Json(name = "size")
    val size: Long?,

    @Json(name = "width")
    val width: Int,

    @Json(name = "height")
    val height: Int

) : Parcelable
