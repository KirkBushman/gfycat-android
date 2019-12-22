package com.kirkbushman.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kirkbushman.sampleapp.activities.GetGfycatActivity
import com.kirkbushman.sampleapp.activities.TrendingGfycatActivity
import com.kirkbushman.sampleapp.activities.VisualizeTokenActivity
import com.kirkbushman.sampleapp.utils.doAsync
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync(doWork = { GfycatApplication.instance?.loadClient() })

        bttn_visualize_token.setOnClickListener {

            VisualizeTokenActivity.start(this)
        }

        bttn_get_gfycats.setOnClickListener {

            GetGfycatActivity.start(this)
        }

        bttn_trenting_gfycats.setOnClickListener {

            TrendingGfycatActivity.start(this)
        }
    }
}
