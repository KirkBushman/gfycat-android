package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.models.Followers
import com.kirkbushman.sampleapp.activities.base.BaseTextPrintActivity

class FollowersActivity : BaseTextPrintActivity<Followers?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, FollowersActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun fetchItem(client: GfycatClient): Followers? {

        return client.followers()
    }
}
