package com.kirkbushman.gfycat.models.http

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AuthRequestBody(

    val client_id: String,
    val client_secret: String,

    val grant_type: String = "client_credentials"
) : Parcelable
