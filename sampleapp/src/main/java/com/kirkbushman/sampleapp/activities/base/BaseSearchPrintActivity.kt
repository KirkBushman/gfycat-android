package com.kirkbushman.sampleapp.activities.base

import android.os.Bundle
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.redgifs.RedgifsClient
import com.kirkbushman.sampleapp.GfycatApplication
import com.kirkbushman.sampleapp.databinding.ActivitySearchPrintBinding
import com.kirkbushman.sampleapp.utils.doAsync

abstract class BaseSearchPrintActivity<T> : BaseActivity() {

    private var item: T? = null

    abstract fun fetchItem(query: String): T

    private lateinit var binding: ActivitySearchPrintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchPrintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        binding.searchBttn.setOnClickListener {

            val query = binding.query.text.trim().toString()

            doAsync(
                doWork = { item = fetchItem(query) },
                onPost = { binding.objText.text = item.toString() }
            )
        }
    }
}
