package com.kirkbushman.gfycat.models.envelopes

import android.os.Parcelable
import com.kirkbushman.gfycat.models.Gfycat
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class GfycatEnvelope(

    @Json(name = "gfyItem")
    val gfyItem: Gfycat

) : Parcelable
