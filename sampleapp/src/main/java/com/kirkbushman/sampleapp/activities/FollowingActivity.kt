package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.models.Following
import com.kirkbushman.sampleapp.activities.base.BaseTextPrintActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FollowingActivity : BaseTextPrintActivity<Following?>() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, FollowingActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: GfycatClient

    override fun fetchItem(): Following? {

        return client.following()
    }
}
