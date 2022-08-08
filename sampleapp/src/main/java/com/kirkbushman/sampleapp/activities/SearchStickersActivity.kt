package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.sampleapp.controllers.GfycatController
import com.kirkbushman.sampleapp.controllers.OnClickCallback
import com.kirkbushman.sampleapp.databinding.ActivityStickersSearchBinding
import com.kirkbushman.sampleapp.utils.doAsync
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchStickersActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, SearchStickersActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: GfycatClient

    private val items = ArrayList<Gfycat>()

    private val controller by lazy {
        GfycatController(object : OnClickCallback {

            override fun onClick(position: Int) {
                val item = items[position]

                GfycatActivity.start(this@SearchStickersActivity, item)
            }
        })
    }

    private lateinit var binding: ActivityStickersSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStickersSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.list.setHasFixedSize(true)
        binding.list.setController(controller)

        binding.searchBttn.setOnClickListener {

            val searchText = binding.query.text.toString()
            items.clear()

            doAsync(
                doWork = { items.addAll(client.stickersSearch(searchText, count = 30) ?: listOf()) },
                onPost = {

                    controller.setItems(items)
                }
            )
        }
    }
}
