package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.sampleapp.R
import kotlinx.android.synthetic.main.activity_gfycat.*

class GfycatActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_GFYCAT = "intent_param_gfycat"

        fun start(context: Context, gfycat: Gfycat) {
            val intent = Intent(context, GfycatActivity::class.java)
            intent.putExtra(PARAM_GFYCAT, gfycat)

            context.startActivity(intent)
        }
    }

    private val gfycat by lazy { intent.getParcelableExtra<Gfycat>(PARAM_GFYCAT)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gfycat)

        gfycat_text.text = gfycat.toString()
    }
}
