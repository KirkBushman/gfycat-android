package com.kirkbushman.redgifs.auth

import com.kirkbushman.gfycat.auth.ClientCredentials
import com.kirkbushman.redgifs.managers.StorageManager
import com.kirkbushman.redgifs.RedgifsClient
import retrofit2.Response

class RedgifsAuthManager(

    private val credentials: ClientCredentials,
    private val logging: Boolean

) : AuthManager() {

    fun getAuthToken(storManager: StorageManager): TokenBearer {

        if (storManager.isAuthed() && storManager.hasToken()) {

            return TokenBearer(this, storManager, storManager.getToken(), credentials)
        } else {

            val api = RedgifsClient.getApi(logging)
            val req = api.getAuthToken(
                clientId = credentials.clientId,
                clientSecret = credentials.clientSecret,
                grantType = credentials.grantType
            )

            val res = req.execute()
            val token = res.body()

            if (!res.isSuccessful || token == null) {
                throw IllegalStateException("Error during authentication")
            }

            return TokenBearer(this, storManager, token, credentials)
        }
    }

    override fun refreshToken(token: Token?): Response<Token> {

        val api = RedgifsClient.getApi(logging)
        val req = api.getAuthToken(
            clientId = credentials.clientId,
            clientSecret = credentials.clientSecret,
            grantType = credentials.grantType
        )

        return req.execute()
    }

    fun getRedgifsClient(bearer: TokenBearer): RedgifsClient {
        return RedgifsClient(bearer, logging)
    }
}
