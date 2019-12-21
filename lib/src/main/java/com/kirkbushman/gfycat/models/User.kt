package com.kirkbushman.gfycat.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class User(

    val userId: String,

    val name: String

) : Parcelable
