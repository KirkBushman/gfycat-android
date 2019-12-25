package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.sampleapp.GfycatApplication
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.utils.doAsync
import kotlinx.android.synthetic.main.activity_get_gfycat.*

class GetGfycatActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, GetGfycatActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val client by lazy { GfycatApplication.instance?.getClient() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_gfycat)

        search_bttn.setOnClickListener {

            val gfyId = query.text.toString()
            var gfycat: Gfycat? = null

            // doAsync(doWork = { gfycat = client?.gfycatFromUrl(gfyId.toUri()) }, onPost = {
            doAsync(doWork = { gfycat = client?.gfycat(gfyId) }, onPost = {

                gfycat_text.text = gfycat.toString()
            })
        }
    }
}
