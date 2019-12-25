package com.kirkbushman.gfycat

import android.net.Uri
import com.kirkbushman.gfycat.auth.TokenBearer
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.gfycat.models.Me
import com.kirkbushman.gfycat.models.User
import com.kirkbushman.gfycat.utils.Utils.buildRetrofit
import com.kirkbushman.gfycat.utils.Utils.getGfyIdFromUrl
import retrofit2.Retrofit

class GfycatClient(private val bearer: TokenBearer, logging: Boolean) {

    companion object {

        @Volatile
        private var retrofit: Retrofit? = null
        @Volatile
        private var api: GfycatApi? = null

        @Synchronized
        fun getRetrofit(logging: Boolean = false): Retrofit {
            return synchronized(this) {

                if (retrofit == null) {
                    retrofit = buildRetrofit(logging)
                }

                retrofit!!
            }
        }

        @Synchronized
        fun getApi(logging: Boolean = false): GfycatApi {
            return synchronized(this) {

                if (api == null) {
                    api = getRetrofit(logging).create(GfycatApi::class.java)
                }

                api!!
            }
        }
    }

    private val api = getApi(logging)

    fun me(): Me? {

        val authMap = getHeaderMap()
        val req = api.me(authMap)
        val res = req.execute()

        if (!res.isSuccessful) {
            return null
        }

        return res.body()
    }

    fun user(userId: String): User? {

        val authMap = getHeaderMap()
        val req = api.user(userId, header = authMap)
        val res = req.execute()

        if (!res.isSuccessful) {
            return null
        }

        return res.body()
    }

    fun userFeed(userId: String, count: Int? = null, cursor: String? = null): List<Gfycat>? {

        val authMap = getHeaderMap()
        val req = api.userFeed(
            userId = userId,
            count = count,
            cursor = cursor,
            header = authMap
        )

        val res = req.execute()
        if (!res.isSuccessful) {
            return null
        }

        return res.body()?.gfycats
    }

    fun gfycat(id: String): Gfycat? {

        val authMap = getHeaderMap()
        val req = api.gfycat(id, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {
            return null
        }

        return res.body()?.gfyItem
    }

    fun gfycatFromUrl(uri: Uri): Gfycat? {

        val gfyId = getGfyIdFromUrl(uri)

        val authMap = getHeaderMap()
        val req = api.gfycat(gfyId, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {
            return null
        }

        return res.body()?.gfyItem
    }

    fun stickers(count: Int? = null, cursor: String? = null): List<Gfycat>? {

        val authMap = getHeaderMap()
        val req = api.stickers(
            count = count,
            cursor = cursor,
            header = authMap
        )

        val res = req.execute()
        if (!res.isSuccessful) {
            return null
        }

        return res.body()?.gfycats
    }

    fun stickersSearch(searchText: String, count: Int? = null, cursor: String? = null): List<Gfycat>? {

        val authMap = getHeaderMap()
        val req = api.stickersSearch(
            searchText = searchText,
            count = count,
            cursor = cursor,
            header = authMap
        )

        val res = req.execute()
        if (!res.isSuccessful) {
            return null
        }

        return res.body()?.gfycats
    }

    fun trendingGfycat(tagName: String? = null, count: Int? = null, cursor: String? = null): List<Gfycat>? {

        val authMap = getHeaderMap()
        val req = api.trendingGfycat(
            tagName = tagName,
            count = count,
            cursor = cursor,
            header = authMap
        )

        val res = req.execute()
        if (!res.isSuccessful) {
            return null
        }

        return res.body()?.gfycats
    }

    fun trendingTags(tagCount: Int? = null, gfyCount: Int? = null, cursor: String? = null): List<String>? {

        val authMap = getHeaderMap()
        val req = api.trendingTags(
            tagCount = tagCount,
            gfyCount = gfyCount,
            cursor = cursor,
            header = authMap
        )

        val res = req.execute()
        if (!res.isSuccessful) {
            return null
        }

        return res.body()
    }

    private fun getHeaderMap(): HashMap<String, String> {
        return hashMapOf("Authorization" to "bearer ".plus(bearer.getRawAccessToken()))
    }
}
