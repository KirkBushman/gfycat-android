package com.kirkbushman.gfycat.models.envelopes

import android.os.Parcelable
import com.kirkbushman.gfycat.models.Gfycat
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class GfycatsEnvelope(

    @Json(name = "gfycats")
    val gfycats: List<Gfycat>,

    @Json(name = "cursor")
    val cursor: String?,

    @Json(name = "found")
    val count: Int?,

    @Json(name = "related")
    val related: List<String>?

) : Parcelable
