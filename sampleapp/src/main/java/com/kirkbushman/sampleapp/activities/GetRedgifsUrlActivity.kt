package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.models.Redgif
import com.kirkbushman.sampleapp.activities.base.BaseSearchPrintActivity

class GetRedgifsUrlActivity : BaseSearchPrintActivity<Redgif?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, GetRedgifsUrlActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun fetchItem(client: GfycatClient, query: String): Redgif? {

        return client.redgifsFromUrl(query.toUri())
    }
}
