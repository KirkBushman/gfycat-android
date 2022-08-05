package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.redgifs.RedgifsClient
import com.kirkbushman.redgifs.models.Redgif
import com.kirkbushman.sampleapp.activities.base.BaseSearchPrintActivity

class GetRedgifsActivity : BaseSearchPrintActivity<Redgif?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, GetRedgifsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun fetchItem(
        gfycatClient: GfycatClient,
        redgifsClient: RedgifsClient,
        query: String
    ): Redgif? {

        return redgifsClient.redgifs(query)
    }
}
