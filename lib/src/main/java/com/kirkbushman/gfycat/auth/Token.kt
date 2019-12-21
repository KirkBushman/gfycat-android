package com.kirkbushman.gfycat.auth

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Token(

    @Json(name = "token_type")
    val tokenType: String,

    @Json(name = "scope")
    val scope: String,

    @Json(name = "expires_in")
    val expiresInMins: Int,

    @Json(name = "access_token")
    val accessToken: String

) : Parcelable
