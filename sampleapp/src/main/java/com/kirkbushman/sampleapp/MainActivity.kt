package com.kirkbushman.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kirkbushman.sampleapp.activities.*
import com.kirkbushman.sampleapp.databinding.ActivityMainBinding
import com.kirkbushman.sampleapp.utils.doAsync

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doAsync(doWork = { GfycatApplication.instance?.loadClients() })

        binding.bttnVisualizeToken.setOnClickListener {

            VisualizeTokenActivity.start(this)
        }

        binding.bttnGetGfycats.setOnClickListener {

            GetGfycatActivity.start(this)
        }

        binding.bttnGetGfycatsFromUrl.setOnClickListener {

            GetGfycatUrlActivity.start(this)
        }

        binding.bttnSearchGfycat.setOnClickListener {

            SearchGfycatActivity.start(this)
        }

        binding.bttnGetRedgifs.setOnClickListener {

            GetRedgifsActivity.start(this)
        }

        binding.bttnGetRedgifsFromUrl.setOnClickListener {

            GetRedgifsUrlActivity.start(this)
        }

        binding.bttnSelf.setOnClickListener {

            SelfActivity.start(this)
        }

        binding.bttnFollowing.setOnClickListener {

            FollowingActivity.start(this)
        }

        binding.bttnFollowers.setOnClickListener {

            FollowersActivity.start(this)
        }

        binding.bttnGetUser.setOnClickListener {

            GetUserActivity.start(this)
        }

        binding.bttnGetUserFeed.setOnClickListener {

            GetUserFeedActivity.start(this)
        }

        binding.bttnStickers.setOnClickListener {

            StickersActivity.start(this)
        }

        binding.bttnStickersSearch.setOnClickListener {

            SearchStickersActivity.start(this)
        }

        binding.bttnReactions.setOnClickListener {

            ReactionsActivity.start(this)
        }

        binding.bttnReactionSearch.setOnClickListener {

            ReactionSearchActivity.start(this)
        }

        binding.bttnTrentingGfycats.setOnClickListener {

            TrendingGfycatActivity.start(this)
        }

        binding.bttnTrentingTags.setOnClickListener {

            TrendingTagsActivity.start(this)
        }
    }
}
