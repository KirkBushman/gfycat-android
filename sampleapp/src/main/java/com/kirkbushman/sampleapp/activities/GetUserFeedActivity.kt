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
import kotlinx.android.synthetic.main.activity_get_user_feed.*
import kotlinx.android.synthetic.main.activity_get_user_feed.list

class GetUserFeedActivity : AppCompatActivity(R.layout.activity_get_user_feed) {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, GetUserFeedActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val items = ArrayList<Gfycat>()

    private val client by lazy { GfycatApplication.instance?.getClient() }
    private val controller by lazy {
        GfycatController(object : OnClickCallback {

            override fun onClick(position: Int) {
                val item = items[position]

                GfycatActivity.start(this@GetUserFeedActivity, item)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list.setHasFixedSize(true)
        list.setController(controller)

        search_bttn.setOnClickListener {

            val userId = query.text.toString()
            items.clear()

            doAsync(doWork = { items.addAll(client?.userFeed(userId) ?: listOf()) }, onPost = {

                controller.setItems(items)
            })
        }
    }
}
