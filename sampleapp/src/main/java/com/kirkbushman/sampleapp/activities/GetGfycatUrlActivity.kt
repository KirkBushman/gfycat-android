package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.redgifs.RedgifsClient
import com.kirkbushman.sampleapp.activities.base.BaseSearchPrintActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GetGfycatUrlActivity : BaseSearchPrintActivity<Gfycat?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, GetGfycatUrlActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: GfycatClient

    override fun fetchItem(query: String): Gfycat? {

        return client.gfycatFromUrl(query.toUri())
    }
}
