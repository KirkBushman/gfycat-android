package com.kirkbushman.gfycat.auth

import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.managers.StorageManager
import com.kirkbushman.gfycat.models.http.AuthBodyClient
import com.kirkbushman.gfycat.models.http.AuthBodyPassword
import com.kirkbushman.gfycat.models.http.AuthBodyRenew
import retrofit2.Response

class GfycatAuthManager(

    private val credentials: Credentials,
    private val logging: Boolean

) : AuthManager() {

    fun getAuthToken(storManager: StorageManager): TokenBearer {

        if (storManager.isAuthed() && storManager.hasToken()) {

            return TokenBearer(this, storManager, storManager.getToken(), credentials)
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

                    return TokenBearer(this, storManager, token, credentials)
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

                    return TokenBearer(this, storManager, token, credentials)
                }

                else -> throw IllegalStateException("Credentials type not found!")
            }
        }
    }

    override fun refreshToken(token: Token?): Response<Token> {

        return when (credentials) {

            is PasswordCredentials -> {

                val api = GfycatClient.getApi()
                val req = api.refreshToken(
                    AuthBodyRenew(
                        client_id = credentials.clientId,
                        client_secret = credentials.clientSecret,
                        refresh_token = token?.refreshToken ?: "",
                        grant_type = "refresh"
                    )
                )

                req.execute()
            }

            is ClientCredentials -> {

                val api = GfycatClient.getApi()
                val req = api.getAuthToken(
                    AuthBodyClient(
                        credentials.clientId,
                        credentials.clientSecret,
                        credentials.grantType
                    )
                )

                req.execute()
            }

            else -> throw IllegalStateException("Credentials type not found!")
        }
    }

    fun getGfycatClient(bearer: TokenBearer): GfycatClient {
        return GfycatClient(bearer, logging)
    }
}
