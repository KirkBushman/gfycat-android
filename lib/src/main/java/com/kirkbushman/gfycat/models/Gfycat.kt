package com.kirkbushman.gfycat.models

import android.os.Parcelable
import com.kirkbushman.gfycat.models.http.ContentUrls
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Gfycat(

    @Json(name = "gfyId")
    val gfyId: String,

    @Json(name = "gfyName")
    val gfyName: String,

    @Json(name = "gfyNumber")
    val gfyNumber: String?,

    @Json(name = "title")
    val title: String?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "avgColor")
    val avgColor: String?,

    @Json(name = "webmUrl")
    val webmUrl: String?,

    @Json(name = "gifUrl")
    val gifUrl: String?,

    @Json(name = "miniUrl")
    val miniUrl: String?,

    @Json(name = "miniPosterUrl")
    val miniPosterUrl: String?,

    @Json(name = "mobileUrl")
    val mobileUrl: String?,

    @Json(name = "mobilePosterUrl")
    val mobilePosterUrl: String?,

    @Json(name = "posterUrl")
    val posterUrl: String?,

    @Json(name = "thumb100PosterUrl")
    val thumb100PosterUrl: String?,

    @Json(name = "max5mbGif")
    val max5mbGif: String?,

    @Json(name = "max2mbGif")
    val max2mbGif: String?,

    @Json(name = "max1mbGif")
    val max1mbGif: String?,

    @Json(name = "gif100px")
    val gif100px: String?,

    @Json(name = "mp4Url")
    val mp4Url: String?,

    @Json(name = "webpUrl")
    val webpUrl: String?,

    @Json(name = "url")
    val url: String?,

    @Json(name = "width")
    val width: Int?,

    @Json(name = "height")
    val height: Int?,

    @Json(name = "content_urls")
    val contentUrls: ContentUrls?,

    @Json(name = "frameRate")
    val frameRate: Float?,

    @Json(name = "numFrames")
    val numFrames: Int?,

    @Json(name = "mp4Size")
    val mp4Size: Int?,

    @Json(name = "webmSize")
    val webmSize: Int?,

    @Json(name = "createDate")
    val createDate: Long,

    @Json(name = "nsfw")
    val nsfw: Boolean?,

    @Json(name = "hasAudio")
    val hasAudio: Boolean?,

    @Json(name = "hasTransparency")
    val hasTransparency: Boolean?,

    @Json(name = "likes")
    val likes: Int,

    @Json(name = "dislikes")
    val dislikes: Int,

    @Json(name = "gatekeeper")
    val gatekeeper: Int?,

    @Json(name = "rating")
    val rating: String?,

    @Json(name = "userName")
    val userName: String?,

    @Json(name = "userDisplayName")
    val userDisplayName: String?,

    @Json(name = "userProfileImageUrl")
    val userProfileImageUrl: String?,

    @Json(name = "extraLemmas")
    val extraLemmas: String?,

    @Json(name = "published")
    val published: Short?,

    @Json(name = "source")
    val source: Int,

    @Json(name = "views")
    val views: Int,

    @Json(name = "tags")
    val tags: List<String>?,

    @Json(name = "languageCategories")
    val languageCategories: List<String>?

) : Parcelable
