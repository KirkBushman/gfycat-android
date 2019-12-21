package com.kirkbushman.gfycat.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Gfycat(

    @Json(name = "gfyId")
    val gfyId: String,

    @Json(name = "gfyName")
    val gfyName: String,

    @Json(name = "gfyNumber")
    val gfyNumber: String

) : Parcelable
