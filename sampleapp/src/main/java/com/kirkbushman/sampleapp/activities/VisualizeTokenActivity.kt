package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.GfycatApplication
import com.kirkbushman.sampleapp.databinding.ActivityVisualizeTokenBinding
import com.kirkbushman.sampleapp.utils.doAsync

class VisualizeTokenActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, VisualizeTokenActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val bearer by lazy { GfycatApplication.instance?.getTokenBearer() }

    private lateinit var binding: ActivityVisualizeTokenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVisualizeTokenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tokenRefresh.setOnClickListener {

            doAsync(
                doWork = { bearer?.renewToken() },
                onPost = {

                    binding.tokenText.text = bearer.toString()
                }
            )
        }

        binding.tokenText.text = bearer.toString()
    }
}
