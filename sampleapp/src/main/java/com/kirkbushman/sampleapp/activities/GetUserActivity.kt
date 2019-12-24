package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.gfycat.models.User
import com.kirkbushman.sampleapp.GfycatApplication
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.utils.doAsync
import kotlinx.android.synthetic.main.activity_get_user.*

class GetUserActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, GetUserActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val client by lazy { GfycatApplication.instance?.getClient() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_user)

        search_bttn.setOnClickListener {

            val userId = query.text.toString()
            var user: User? = null

            doAsync(doWork = { user = client?.user(userId) }, onPost = {

                user_text.text = user.toString()
            })
        }
    }
}
