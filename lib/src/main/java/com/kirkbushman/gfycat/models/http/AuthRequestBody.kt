package com.kirkbushman.gfycat.models.http

data class AuthRequestBody(

    val client_id: String,
    val client_secret: String,

    val grant_type: String = "client_credentials"
)
