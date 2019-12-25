package com.kirkbushman.gfycat.auth

import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.managers.StorageManager
import com.kirkbushman.gfycat.models.http.AuthBodyRenew

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

    fun renewToken() {

        if (token == null || token?.refreshToken == null) {
            return
        }

        val api = GfycatClient.getApi()
        val req = api.refreshToken(
            AuthBodyRenew(
                client_id = credentials.clientId,
                client_secret = credentials.clientSecret,
                refresh_token = token?.refreshToken ?: "",
                grant_type = "refresh"
            )
        )

        val res = req.execute()
        val token = res.body()

        if (!res.isSuccessful || token == null) {
            throw IllegalStateException("Error during token renewal")
        }

        this.token = token

        storManager.saveToken(token)
    }
}
