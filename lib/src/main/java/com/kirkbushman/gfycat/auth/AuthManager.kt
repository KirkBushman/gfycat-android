package com.kirkbushman.gfycat.auth

import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.managers.StorageManager
import com.kirkbushman.gfycat.models.http.AuthBodyClient
import com.kirkbushman.gfycat.models.http.AuthBodyPassword

class AuthManager(private val credentials: Credentials, private val logging: Boolean) {

    fun getAuthToken(storManager: StorageManager): TokenBearer {

        if (storManager.isAuthed() && storManager.hasToken()) {

            return TokenBearer(storManager, storManager.getToken(), credentials)
        } else {

            val api = GfycatClient.getApi(logging)

            when (credentials) {

                is ClientCredentials -> {

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

                    return TokenBearer(storManager, token, credentials)
                }

                is PasswordCredentials -> {

                    val req = api.getAuthToken(
                        AuthBodyPassword(
                            credentials.clientId,
                            credentials.clientSecret,
                            credentials.username,
                            credentials.password,
                            credentials.grantType
                        )
                    )

                    val res = req.execute()
                    val token = res.body()

                    if (!res.isSuccessful || token == null) {
                        throw IllegalStateException("Error during authentication")
                    }

                    return TokenBearer(storManager, token, credentials)
                }

                else -> throw IllegalStateException("Credentials type not found!")
            }
        }
    }

    fun getGfycatClient(bearer: TokenBearer): GfycatClient {
        return GfycatClient(bearer, logging)
    }
}
