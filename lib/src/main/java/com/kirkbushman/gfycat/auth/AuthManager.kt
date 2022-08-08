package com.kirkbushman.gfycat.auth

import retrofit2.Response

abstract class AuthManager {

    abstract fun refreshToken(token: Token?): Response<Token>
}
