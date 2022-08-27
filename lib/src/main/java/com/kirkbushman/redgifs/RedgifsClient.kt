package com.kirkbushman.redgifs

import android.net.Uri
import com.kirkbushman.gfycat.utils.Utils
import com.kirkbushman.redgifs.auth.TokenBearer
import com.kirkbushman.redgifs.models.Redgif
import retrofit2.Retrofit

class RedgifsClient(val bearer: TokenBearer, logging: Boolean) {

    companion object {

        @Volatile
        private var retrofit: Retrofit? = null
        @Volatile
        private var api: RedgifsApi? = null

        @JvmStatic
        @Synchronized
        fun getRetrofit(logging: Boolean = false): Retrofit {

            if (retrofit == null) {

                synchronized(this) {

                    retrofit = Utils.buildRedgifsRetrofit(logging)
                }
            }

            return retrofit!!
        }

        @JvmStatic
        @Synchronized
        fun getApi(logging: Boolean = false): RedgifsApi {

            if (api == null) {

                synchronized(this) {

                    api = getRetrofit(logging).create(RedgifsApi::class.java)
                }
            }

            return api!!
        }
    }

    private val api by lazy { getApi(logging) }

    fun redgifs(id: String): Redgif? {

        val authMap = getHeaderMap()
        val req = api.redgifs(
            gifId = id,
            header = authMap
        )

        val res = req.execute()
        if (!res.isSuccessful) {
            return null
        }

        return res.body()?.gif
    }

    fun redgifsFromUrl(uri: Uri): Redgif? {

        var gfyId = Utils.getGfyIdFromUrl(uri)
        if (gfyId.contains('-')) {

            // some urls include other params after the '-' symbol,
            // remove them in order to get the id
            gfyId = gfyId.replace(
                gfyId.substring(gfyId.indexOfFirst { it == '-' }), ""
            )
        }

        return redgifs(gfyId)
    }

    private fun getHeaderMap(): HashMap<String, String> {
        return hashMapOf("Authorization" to "Bearer ".plus(bearer.getRawAccessToken()))
    }
}