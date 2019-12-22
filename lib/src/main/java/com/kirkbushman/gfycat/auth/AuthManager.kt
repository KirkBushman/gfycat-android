package com.kirkbushman.gfycat.auth

import com.kirkbushman.gfycat.GfycatApi
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.managers.StorageManager
import com.kirkbushman.gfycat.models.http.AuthRequestBody
import com.kirkbushman.gfycat.utils.Utils.getRetrofit

class AuthManager(private val credentials: Credentials, private val logging: Boolean) {

    private val retrofit = getRetrofit(logging)
    private val api = retrofit.create(GfycatApi::class.java)

    fun getAuthToken(storManager: StorageManager): TokenBearer {

        if (storManager.isAuthed() && storManager.hasToken()) {

            return TokenBearer(storManager, storManager.getToken(), credentials)
        } else {

            val req = api.getAuthToken(AuthRequestBody(credentials.clientId, credentials.clientSecret))
            val res = req.execute()

            val token = res.body()

            if (!res.isSuccessful || token == null) {
                throw IllegalStateException("Error during authentication")
            }

            return TokenBearer(storManager, token, credentials)
        }
    }

    fun getGfycatClient(bearer: TokenBearer): GfycatClient {
        return GfycatClient(bearer, logging)
    }
}
