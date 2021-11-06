package com.kirkbushman.gfycat.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Redgif(

    @Json(name = "id")
    val id: String,

    @Json(name = "hasAudio")
    val hasAudio: Boolean?,

    @Json(name = "width")
    val width: Int?,

    @Json(name = "height")
    val height: Int?,

    @Json(name = "views")
    val views: Long?,

    @Json(name = "published")
    val published: Boolean?,

    @Json(name = "urls")
    val urls: RedgifMediaUrls?,

    @Json(name = "userName")
    val userName: String?,

    @Json(name = "avgColor")
    val avgColor: String?

) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class RedgifMediaUrls(

    @Json(name = "sd")
    val sd: String?,

    @Json(name = "hd")
    val hd: String?,

    @Json(name = "gif")
    val gif: String?,

    @Json(name = "poster")
    val poster: String?,

    @Json(name = "thumbnail")
    val thumbnail: String?,

    @Json(name = "vthumbnail")
    val vThumbnail: String?

) : Parcelable
