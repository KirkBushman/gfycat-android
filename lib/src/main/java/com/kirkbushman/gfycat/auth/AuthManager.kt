package com.kirkbushman.gfycat.auth

import com.kirkbushman.gfycat.GfycatApi
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.utils.Utils.getRetrofit

class AuthManager(private val clientId: String, private val clientSecret: String, logging: Boolean) {

    private val retrofit = getRetrofit(logging)
    private val api = retrofit.create(GfycatApi::class.java)

    fun getAuthToken(logging: Boolean): GfycatClient {

        val req = api.getAuthToken(clientId, clientSecret)
        val res = req.execute()

        val token = res.body()

        if (!res.isSuccessful || token == null) {
            throw IllegalStateException("Error during authentication")
        }

        return GfycatClient(token, logging)
    }
}
