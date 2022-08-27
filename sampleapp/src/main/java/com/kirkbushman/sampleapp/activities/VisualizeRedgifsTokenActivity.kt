package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.auth.TokenBearer
import com.kirkbushman.redgifs.RedgifsClient
import com.kirkbushman.sampleapp.databinding.ActivityVisualizeTokenBinding
import com.kirkbushman.sampleapp.utils.doAsync
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VisualizeRedgifsTokenActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, VisualizeRedgifsTokenActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: RedgifsClient

    private lateinit var binding: ActivityVisualizeTokenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVisualizeTokenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bearer = client.bearer

        binding.tokenRefresh.setOnClickListener {

            doAsync(
                doWork = { bearer.renewToken() },
                onPost = {

                    binding.tokenText.text = bearer.toString()
                }
            )
        }

        binding.tokenText.text = bearer.toString()
    }
}
