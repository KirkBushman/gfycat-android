package com.kirkbushman.gfycat.models.http

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AuthBodyRenew(

    val client_id: String,
    val client_secret: String,
    val refresh_token: String,
    val grant_type: String
) : Parcelable
