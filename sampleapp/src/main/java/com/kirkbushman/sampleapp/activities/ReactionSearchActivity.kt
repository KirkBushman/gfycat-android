package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.models.Tag
import com.kirkbushman.redgifs.RedgifsClient
import com.kirkbushman.sampleapp.activities.base.BaseSearchPrintActivity

class ReactionSearchActivity : BaseSearchPrintActivity<Tag?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, ReactionSearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun fetchItem(
        gfycatClient: GfycatClient,
        redgifsClient: RedgifsClient,
        query: String
    ): Tag? {

        return gfycatClient.reactionGfycat(tagName = query, gfyCount = 8)
    }
}
