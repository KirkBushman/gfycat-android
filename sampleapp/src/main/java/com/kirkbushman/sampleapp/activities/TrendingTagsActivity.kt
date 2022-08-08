package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.sampleapp.GfycatApplication
import com.kirkbushman.sampleapp.controllers.OnClickCallback
import com.kirkbushman.sampleapp.controllers.TagsController
import com.kirkbushman.sampleapp.databinding.ActivityTrendingTagsBinding
import com.kirkbushman.sampleapp.utils.doAsync
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TrendingTagsActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, TrendingTagsActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: GfycatClient

    private val items = ArrayList<String>()

    private val controller by lazy {
        TagsController(object : OnClickCallback {

            override fun onClick(position: Int) {}
        })
    }

    private lateinit var binding: ActivityTrendingTagsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTrendingTagsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.list.setHasFixedSize(true)
        binding.list.setController(controller)

        doAsync(
            doWork = { items.addAll(client.trendingTags(tagCount = 30) ?: listOf()) },
            onPost = {

                controller.setItems(items)
            }
        )
    }
}
