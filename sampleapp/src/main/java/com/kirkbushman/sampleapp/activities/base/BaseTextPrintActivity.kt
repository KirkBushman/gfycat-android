package com.kirkbushman.sampleapp.activities.base

import android.os.Bundle
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.sampleapp.GfycatApplication
import com.kirkbushman.sampleapp.databinding.ActivityTextPrintBinding
import com.kirkbushman.sampleapp.utils.doAsync

abstract class BaseTextPrintActivity<T> : BaseActivity() {

    private var item: T? = null

    abstract fun fetchItem(): T

    private lateinit var binding: ActivityTextPrintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTextPrintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        doAsync(
            doWork = { item = fetchItem() },
            onPost = { binding.objText.text = item.toString() }
        )
    }
}
