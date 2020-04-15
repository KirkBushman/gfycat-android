package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.models.ReactionTags
import com.kirkbushman.sampleapp.activities.base.BaseTextPrintActivity

class ReactionsActivity : BaseTextPrintActivity<ReactionTags?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, ReactionsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun fetchItem(client: GfycatClient): ReactionTags? {

        return client.reactionGfycats(gfyCount = 1)
    }
}
