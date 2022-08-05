package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.sampleapp.GfycatApplication
import com.kirkbushman.sampleapp.controllers.GfycatController
import com.kirkbushman.sampleapp.controllers.OnClickCallback
import com.kirkbushman.sampleapp.databinding.ActivityStickersBinding
import com.kirkbushman.sampleapp.utils.doAsync

class StickersActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, StickersActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val items = ArrayList<Gfycat>()

    private val client by lazy { GfycatApplication.instance?.getGfycatClient() }
    private val controller by lazy {
        GfycatController(object : OnClickCallback {

            override fun onClick(position: Int) {
                val item = items[position]

                GfycatActivity.start(this@StickersActivity, item)
            }
        })
    }

    private lateinit var binding: ActivityStickersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStickersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.list.setHasFixedSize(true)
        binding.list.setController(controller)

        doAsync(
            doWork = { items.addAll(client?.stickers(count = 30) ?: listOf()) },
            onPost = {

                controller.setItems(items)
            }
        )
    }
}
