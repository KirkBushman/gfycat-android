package com.kirkbushman.redgifs.models.envelopes

import android.os.Parcelable
import com.kirkbushman.redgifs.models.Redgif
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class RedgifEnvelope(

    @Json(name = "gif")
    val gif: Redgif?

) : Parcelable
