package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.models.Me
import com.kirkbushman.sampleapp.activities.base.BaseTextPrintActivity

class SelfActivity : BaseTextPrintActivity<Me?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, SelfActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun fetchItem(client: GfycatClient): Me? {

        return client.me()
    }
}
