package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.sampleapp.GfycatApplication
import com.kirkbushman.sampleapp.controllers.GfycatController
import com.kirkbushman.sampleapp.controllers.OnClickCallback
import com.kirkbushman.sampleapp.databinding.ActivityGetUserFeedBinding
import com.kirkbushman.sampleapp.utils.doAsync

class GetUserFeedActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, GetUserFeedActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val items = ArrayList<Gfycat>()

    private val client by lazy { GfycatApplication.instance?.getGfycatClient() }
    private val controller by lazy {
        GfycatController(object : OnClickCallback {

            override fun onClick(position: Int) {
                val item = items[position]

                GfycatActivity.start(this@GetUserFeedActivity, item)
            }
        })
    }

    private lateinit var binding: ActivityGetUserFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetUserFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.list.setHasFixedSize(true)
        binding.list.setController(controller)

        binding.searchBttn.setOnClickListener {

            val userId = binding.query.text.toString()
            items.clear()

            doAsync(
                doWork = { items.addAll(client?.userFeed(userId) ?: listOf()) },
                onPost = {

                    controller.setItems(items)
                }
            )
        }
    }
}
