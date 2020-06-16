package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.sampleapp.activities.base.BaseSearchPrintActivity

class GetRedgifsActivity : BaseSearchPrintActivity<Gfycat?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, GetRedgifsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun fetchItem(client: GfycatClient, query: String): Gfycat? {

        return client.redgifs(query)
    }
}
