package com.kirkbushman.gfycat.auth

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Credentials(val clientId: String, val clientSecret: String) : Parcelable
