package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.GfycatApplication
import com.kirkbushman.sampleapp.R
import kotlinx.android.synthetic.main.activity_visualize_token.*

class VisualizeTokenActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, VisualizeTokenActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val bearer by lazy { GfycatApplication.instance?.getTokenBearer() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualize_token)

        token_text.text = bearer.toString()
    }
}