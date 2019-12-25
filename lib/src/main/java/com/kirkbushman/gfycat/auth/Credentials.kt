package com.kirkbushman.gfycat.auth

interface Credentials {

    val clientId: String
    val clientSecret: String
    val grantType: String
}
