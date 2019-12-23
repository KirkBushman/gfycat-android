package com.kirkbushman.gfycat.auth

import com.kirkbushman.gfycat.managers.StorageManager

data class TokenBearer(

    private val storManager: StorageManager,

    private var token: Token?,

    private val credentials: Credentials
) {

    init {

        if (token != null) {
            storManager.saveToken(token!!)
        }
    }

    fun getRawAccessToken(): String? {

        if (shouldRenew()) {
            renewToken()
        }

        return token!!.accessToken
    }

    fun getAuthHeader(): Map<String, String> {
        return hashMapOf("Authorization" to "bearer ".plus(getRawAccessToken()))
    }

    private fun shouldRenew(): Boolean {
        return token?.shouldRenew() ?: false
    }

    fun renewToken() {}
}
