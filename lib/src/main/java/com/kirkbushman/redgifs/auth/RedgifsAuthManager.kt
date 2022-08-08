package com.kirkbushman.redgifs.auth

import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.auth.AuthManager
import com.kirkbushman.gfycat.auth.ClientCredentials
import com.kirkbushman.gfycat.auth.Token
import com.kirkbushman.gfycat.auth.TokenBearer
import com.kirkbushman.gfycat.managers.StorageManager
import com.kirkbushman.gfycat.models.http.AuthBodyClient
import com.kirkbushman.gfycat.models.http.AuthBodyRenew
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

            // val api = RedgifsClient.getApi(logging)
            val api = GfycatClient.getApi(logging)
            val req = api.getAuthToken(
                AuthBodyClient(
                    credentials.clientId,
                    credentials.clientSecret,
                    credentials.grantType
                )
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

        val api = GfycatClient.getApi()
        val req = api.refreshToken(
            AuthBodyRenew(
                client_id = credentials.clientId,
                client_secret = credentials.clientSecret,
                refresh_token = token?.refreshToken ?: "",
                grant_type = "refresh"
            )
        )

        return req.execute()
    }

    fun getRedgifsClient(bearer: TokenBearer): RedgifsClient {
        return RedgifsClient(bearer, logging)
    }
}
