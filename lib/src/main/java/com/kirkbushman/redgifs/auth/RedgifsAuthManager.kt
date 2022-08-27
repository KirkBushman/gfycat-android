package com.kirkbushman.redgifs.auth

import android.util.Log
import com.kirkbushman.gfycat.auth.ClientCredentials
import com.kirkbushman.redgifs.managers.StorageManager
import com.kirkbushman.redgifs.RedgifsClient
import com.kirkbushman.redgifs.models.http.AuthBodyClient
import com.kirkbushman.redgifs.models.http.AuthBodyRenew
import okhttp3.ResponseBody
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

        val api = RedgifsClient.getApi()
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
