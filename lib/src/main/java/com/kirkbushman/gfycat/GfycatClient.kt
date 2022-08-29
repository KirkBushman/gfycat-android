package com.kirkbushman.gfycat

import android.net.Uri
import com.kirkbushman.gfycat.auth.TokenBearer
import com.kirkbushman.gfycat.models.*
import com.kirkbushman.gfycat.utils.Utils
import com.kirkbushman.gfycat.utils.Utils.getGfyIdFromUrl
import retrofit2.Retrofit

class GfycatClient(val bearer: TokenBearer, logging: Boolean) {

    companion object {

        @Volatile
        private var retrofit: Retrofit? = null

        @Volatile
        private var api: GfycatApi? = null

        @JvmStatic
        @Synchronized
        fun getRetrofit(logging: Boolean = false): Retrofit {

            if (retrofit == null) {

                synchronized(this) {

                    retrofit = Utils.buildGfycatRetrofit(logging)
                }
            }

            return retrofit!!
        }

        @JvmStatic
        @Synchronized
        fun getApi(logging: Boolean = false): GfycatApi {

            if (api == null) {

                synchronized(this) {

                    api = getRetrofit(logging).create(GfycatApi::class.java)
                }
            }

            return api!!
        }
    }

    private val api by lazy { getApi(logging) }

    fun me(): Me? {

        val authMap = getHeaderMap()
        val req = api.me(authMap)
        val res = req.execute()

        if (!res.isSuccessful) {
            return null
        }

        return res.body()
    }

    fun following(): Following? {

        val authMap = getHeaderMap()
        val req = api.following(authMap)
        val res = req.execute()

        if (!res.isSuccessful) {
            return null
        }

        return res.body()
    }

    fun followers(): Followers? {

        val authMap = getHeaderMap()
        val req = api.followers(authMap)
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
        val req = api.gfycat(
            gfyId = id,
            header = authMap
        )

        val res = req.execute()
        if (!res.isSuccessful) {
            return null
        }

        return res.body()?.gfyItem
    }

    fun gfycatFromUrl(uri: Uri): Gfycat? {

        var gfyId = getGfyIdFromUrl(uri)
        if (gfyId.contains('-')) {

            // some urls include other params after the '-' symbol,
            // remove them in order to get the id
            gfyId = gfyId.replace(gfyId.substring(gfyId.indexOfFirst { it == '-' }), "")
        }

        return gfycat(gfyId)
    }

    fun gfycatsSearch(searchText: String, count: Int? = null, cursor: String? = null): List<Gfycat>? {

        val authMap = getHeaderMap()
        val req = api.searchGfycats(
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

    fun reactionGfycats(gfyCount: Int? = null, locale: Locale? = null, cursor: String? = null): ReactionTags? {

        val authMap = getHeaderMap()
        val req = api.reactionGfycats(
            gfyCount = gfyCount,
            locale = locale?.valueStr,
            cursor = cursor,
            header = authMap
        )

        val res = req.execute()
        if (!res.isSuccessful) {
            return null
        }

        return res.body()
    }

    fun reactionGfycat(tagName: String, gfyCount: Int? = null, locale: Locale? = null, cursor: String? = null): Tag? {

        val authMap = getHeaderMap()
        val req = api.reactionGfycat(
            tagName = tagName,
            gfyCount = gfyCount,
            locale = locale?.valueStr,
            cursor = cursor,
            header = authMap
        )

        val res = req.execute()
        if (!res.isSuccessful) {
            return null
        }

        return res.body()
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
        return hashMapOf("Authorization" to "Bearer ".plus(bearer.getRawAccessToken()))
    }
}
