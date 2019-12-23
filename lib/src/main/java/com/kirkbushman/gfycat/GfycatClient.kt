package com.kirkbushman.gfycat

import com.kirkbushman.gfycat.auth.TokenBearer
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.gfycat.models.User
import com.kirkbushman.gfycat.utils.Utils.getRetrofit

class GfycatClient(private val bearer: TokenBearer, logging: Boolean) {

    private val retrofit = getRetrofit(logging)
    private val api = retrofit.create(GfycatApi::class.java)

    fun me(): User? {

        val authMap = getHeaderMap()
        val req = api.me(authMap)
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

    fun trendingTags(): List<String>? {

        val authMap = getHeaderMap()
        val req = api.trendingTags(
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
