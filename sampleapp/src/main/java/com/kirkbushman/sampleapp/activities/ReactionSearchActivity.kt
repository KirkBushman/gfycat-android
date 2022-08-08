package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.models.Tag
import com.kirkbushman.redgifs.RedgifsClient
import com.kirkbushman.sampleapp.activities.base.BaseSearchPrintActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReactionSearchActivity : BaseSearchPrintActivity<Tag?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, ReactionSearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var gfycatClient: GfycatClient

    override fun fetchItem(query: String): Tag? {

        return gfycatClient.reactionGfycat(tagName = query, gfyCount = 8)
    }
}
