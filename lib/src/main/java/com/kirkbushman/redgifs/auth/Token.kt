package com.kirkbushman.redgifs.auth

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Token(

    @Json(name = "token_type")
    val tokenType: String,

    @Json(name = "scope")
    val scope: String,

    @Json(name = "resource_owner")
    val resourceOwner: String?,

    @Json(name = "refresh_token")
    val refreshToken: String?,

    @Json(name = "refresh_token_expires_in")
    val refreshTokenExpiresIn: Int?,

    @Json(name = "access_token")
    val accessToken: String,

    @Json(name = "expires_in")
    val expiresIn: Int,

    @Transient
    val createdTime: Long = (System.currentTimeMillis() / 1000L)

) : Parcelable {

    @IgnoredOnParcel
    val expirationTime by lazy { createdTime + expiresIn }

    @IgnoredOnParcel
    val expirationTimeRefresh by lazy { if (refreshTokenExpiresIn == null) null else createdTime + refreshTokenExpiresIn }

    /**
     * The token should last an hour,
     * consider it safe for 55 mins, for precaution
     * 5 mins = 5 * 60 = 300
     */
    fun shouldRenew(): Boolean {
        val currentTimestamp = System.currentTimeMillis() / 1000L
        return (currentTimestamp + 300L) >= expirationTime
    }
}
