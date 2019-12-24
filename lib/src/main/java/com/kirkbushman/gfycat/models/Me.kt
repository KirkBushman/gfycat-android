package com.kirkbushman.gfycat.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Me(

    @Json(name = "userid")
    val userId: String,

    @Json(name = "username")
    val username: String,

    @Json(name = "email")
    val email: String,

    @Json(name = "description")
    val description: String?,

    @Json(name = "iframeProfileImageVisible")
    val iFrameProfileImageVisible: Boolean,

    @Json(name = "verified")
    val verified: Boolean,

    @Json(name = "publishedGfycats")
    val publishedGfycats: Int,

    @Json(name = "followers")
    val followers: Int,

    @Json(name = "following")
    val following: Int

) : Parcelable
