package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.redgifs.RedgifsClient
import com.kirkbushman.redgifs.models.Redgif
import com.kirkbushman.sampleapp.activities.base.BaseSearchPrintActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GetRedgifsUrlActivity : BaseSearchPrintActivity<Redgif?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, GetRedgifsUrlActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var redgifsClient: RedgifsClient

    override fun fetchItem(query: String): Redgif? {

        return redgifsClient.redgifsFromUrl(query.toUri())
    }
}
