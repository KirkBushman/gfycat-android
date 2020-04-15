package com.kirkbushman.sampleapp.activities.base

import android.os.Bundle
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.sampleapp.GfycatApplication
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.utils.doAsync
import kotlinx.android.synthetic.main.activity_text_print.*

abstract class BaseTextPrintActivity<T> : BaseActivity(R.layout.activity_text_print) {

    private val client by lazy { GfycatApplication.instance?.getClient() }

    private var item: T? = null

    abstract fun fetchItem(client: GfycatClient): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        doAsync(
            doWork = { item = fetchItem(client!!) },
            onPost = { obj_text.text = item.toString() }
        )
    }
}
