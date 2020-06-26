package com.kirkbushman.gfycat.models.http

import android.os.Parcelable
import com.kirkbushman.gfycat.models.GfycatUrl
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ContentUrls(

    @Json(name = "100pxGif")
    val size100PxGif: GfycatUrl?,

    @Json(name = "max1mbGif")
    val max1mbGif: GfycatUrl?,

    @Json(name = "max2mbGif")
    val max2mbGif: GfycatUrl?,

    @Json(name = "max5mbGif")
    val max5mbGif: GfycatUrl?,

    @Json(name = "largeGif")
    val largeGif: GfycatUrl?,

    @Json(name = "webp")
    val webp: GfycatUrl?,

    @Json(name = "webm")
    val webm: GfycatUrl?,

    @Json(name = "mp4")
    val mp4: GfycatUrl?,

    @Json(name = "mobile")
    val mobile: GfycatUrl?

) : Parcelable
