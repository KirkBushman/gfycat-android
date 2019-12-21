package com.kirkbushman.gfycat

import com.kirkbushman.gfycat.auth.Token
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.gfycat.models.User
import com.kirkbushman.gfycat.utils.Utils.getRetrofit

class GfycatClient(private val token: Token, logging: Boolean) {

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

    fun gfycat(id: String): Gfycat? {

        val authMap = getHeaderMap()
        val req = api.gfycat(id, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {
            return null
        }

        return res.body()
    }

    private fun getHeaderMap(): HashMap<String, String> {
        return hashMapOf("Authorization" to "bearer ".plus(token.accessToken))
    }
}
