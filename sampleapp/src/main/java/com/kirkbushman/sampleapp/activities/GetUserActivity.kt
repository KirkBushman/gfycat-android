package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.models.User
import com.kirkbushman.sampleapp.activities.base.BaseSearchPrintActivity

class GetUserActivity : BaseSearchPrintActivity<User?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, GetUserActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun fetchItem(client: GfycatClient, query: String): User? {

        return client.user(query)
    }
}
