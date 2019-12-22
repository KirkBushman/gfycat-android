package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.sampleapp.GfycatApplication
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.controllers.GfycatController
import com.kirkbushman.sampleapp.controllers.OnClickCallback
import com.kirkbushman.sampleapp.utils.doAsync
import kotlinx.android.synthetic.main.activity_trending_gfycat.*

class TrendingGfycatActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, TrendingGfycatActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val client by lazy { GfycatApplication.instance?.getClient() }
    private val controller by lazy {
        GfycatController(object : OnClickCallback {

            override fun onClick(position: Int) {

            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trending_gfycat)

        list.setHasFixedSize(true)
        list.setController(controller)

        val items = ArrayList<Gfycat>()

        doAsync(doWork = { items.addAll(client?.trendingGfycat(count = 10) ?: listOf()) }, onPost = {

            controller.setItems(items)
        })
    }
}