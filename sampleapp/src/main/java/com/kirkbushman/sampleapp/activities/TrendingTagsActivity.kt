package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.GfycatApplication
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.controllers.OnClickCallback
import com.kirkbushman.sampleapp.controllers.TagsController
import com.kirkbushman.sampleapp.utils.doAsync
import kotlinx.android.synthetic.main.activity_trending_tags.*

class TrendingTagsActivity : AppCompatActivity(R.layout.activity_trending_tags) {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, TrendingTagsActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val items = ArrayList<String>()

    private val client by lazy { GfycatApplication.instance?.getClient() }
    private val controller by lazy {
        TagsController(object : OnClickCallback {

            override fun onClick(position: Int) {}
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list.setHasFixedSize(true)
        list.setController(controller)

        doAsync(
            doWork = { items.addAll(client?.trendingTags(tagCount = 30) ?: listOf()) },
            onPost = {

                controller.setItems(items)
            }
        )
    }
}
