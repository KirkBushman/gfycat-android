package com.kirkbushman.gfycat.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

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

    @Json(name = "profileUrl")
    val profileUrl: String?,

    @Json(name = "profileImageUrl")
    val profileImageUrl: String?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "views")
    val views: Int,

    @Json(name = "uploadNotices")
    val uploadNotices: Boolean?,

    @Json(name = "emailVerified")
    val emailVerified: Boolean?,

    @Json(name = "url")
    val url: String,

    @Json(name = "createDate")
    val createDate: Long,

    @Json(name = "iframeProfileImageVisible")
    val iFrameProfileImageVisible: Boolean,

    @Json(name = "verified")
    val verified: Boolean,

    @Json(name = "publishedGfycats")
    val publishedGfycats: Int,

    @Json(name = "totalGfycats")
    val totalGfycats: Int,

    @Json(name = "totalBookmarks")
    val totalBookmarks: Int,

    @Json(name = "totalAlbums")
    val totalAlbums: Int,

    @Json(name = "publishedAlbums")
    val publishedAlbums: Int,

    @Json(name = "subscription")
    val subscription: Int,

    @Json(name = "followers")
    val followers: Int,

    @Json(name = "following")
    val following: Int

) : Parcelable
