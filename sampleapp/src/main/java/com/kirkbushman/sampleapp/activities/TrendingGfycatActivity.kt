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

class TrendingGfycatActivity : AppCompatActivity(R.layout.activity_trending_gfycat) {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, TrendingGfycatActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val items = ArrayList<Gfycat>()

    private val client by lazy { GfycatApplication.instance?.getClient() }
    private val controller by lazy {
        GfycatController(object : OnClickCallback {

            override fun onClick(position: Int) {
                val item = items[position]

                GfycatActivity.start(this@TrendingGfycatActivity, item)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list.setHasFixedSize(true)
        list.setController(controller)

        doAsync(doWork = { items.addAll(client?.trendingGfycat(count = 30) ?: listOf()) }, onPost = {

            controller.setItems(items)
        })
    }
}
