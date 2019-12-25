package com.kirkbushman.gfycat.auth

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PasswordCredentials(

    override val clientId: String,
    override val clientSecret: String,

    val username: String,
    val password: String,

    override val grantType: String = "password"
) : Parcelable, Credentials
