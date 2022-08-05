package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.redgifs.RedgifsClient
import com.kirkbushman.sampleapp.activities.base.BaseSearchPrintActivity

class GetGfycatActivity : BaseSearchPrintActivity<Gfycat?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, GetGfycatActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun fetchItem(
        gfycatClient: GfycatClient,
        redgifsClient: RedgifsClient,
        query: String
    ): Gfycat? {

        return gfycatClient.gfycat(query)
    }
}
