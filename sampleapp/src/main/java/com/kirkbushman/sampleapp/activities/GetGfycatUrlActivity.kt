package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.sampleapp.activities.base.BaseSearchPrintActivity

class GetGfycatUrlActivity : BaseSearchPrintActivity<Gfycat?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, GetGfycatUrlActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun fetchItem(client: GfycatClient, query: String): Gfycat? {

        return client.gfycatFromUrl(query.toUri())
    }
}
