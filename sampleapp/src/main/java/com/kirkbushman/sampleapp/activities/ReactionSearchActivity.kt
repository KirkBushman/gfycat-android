package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.models.Tag
import com.kirkbushman.sampleapp.activities.base.BaseSearchPrintActivity

class ReactionSearchActivity : BaseSearchPrintActivity<Tag?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, ReactionSearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun fetchItem(client: GfycatClient, query: String): Tag? {

        return client.reactionGfycat(tagName = query, gfyCount = 8)
    }
}
