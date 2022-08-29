package com.kirkbushman.gfycat.auth

import com.kirkbushman.gfycat.managers.StorageManager

data class TokenBearer(

    private val authManager: AuthManager,
    private val storManager: StorageManager,
    private var token: Token?,
    private val credentials: Credentials

) {

    init {

        if (token != null) {
            storManager.saveToken(token!!)
        }
    }

    fun getRawAccessToken(): String {

        if (shouldRenew()) {
            renewToken()
        }

        return token!!.accessToken
    }

    fun getAuthHeader(): Map<String, String> {
        return hashMapOf("Authorization" to "Bearer ".plus(getRawAccessToken()))
    }

    private fun shouldRenew(): Boolean {
        return token?.shouldRenew() ?: false
    }

    fun renewToken() {

        if (token == null) {
            return
        }

        val res = authManager.refreshToken(token)
        val token = res.body()

        if (!res.isSuccessful || token == null) {
            throw IllegalStateException("Error during token renewal")
        }

        this.token = token

        storManager.saveToken(token)
    }
}
