package com.kirkbushman.gfycat.auth

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClientCredentials(

    override val clientId: String,
    override val clientSecret: String,

    override val grantType: String = "client_credentials"

) : Parcelable, Credentials
