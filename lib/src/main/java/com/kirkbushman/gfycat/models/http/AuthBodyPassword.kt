package com.kirkbushman.gfycat.models.http

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AuthBodyPassword(

    val client_id: String,
    val client_secret: String,
    val username: String,
    val password: String,
    val grant_type: String
) : Parcelable
