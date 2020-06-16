package com.kirkbushman.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kirkbushman.sampleapp.activities.*
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

        bttn_get_gfycats_from_url.setOnClickListener {

            GetGfycatUrlActivity.start(this)
        }

        bttn_get_redgifs.setOnClickListener {

            GetRedgifsActivity.start(this)
        }

        bttn_get_redgifs_from_url.setOnClickListener {

            GetRedgifsUrlActivity.start(this)
        }

        bttn_self.setOnClickListener {

            SelfActivity.start(this)
        }

        bttn_following.setOnClickListener {

            FollowingActivity.start(this)
        }

        bttn_followers.setOnClickListener {

            FollowersActivity.start(this)
        }

        bttn_get_user.setOnClickListener {

            GetUserActivity.start(this)
        }

        bttn_get_user_feed.setOnClickListener {

            GetUserFeedActivity.start(this)
        }

        bttn_stickers.setOnClickListener {

            StickersActivity.start(this)
        }

        bttn_stickers_search.setOnClickListener {

            SearchStickersActivity.start(this)
        }

        bttn_reactions.setOnClickListener {

            ReactionsActivity.start(this)
        }

        bttn_reaction_search.setOnClickListener {

            ReactionSearchActivity.start(this)
        }

        bttn_trenting_gfycats.setOnClickListener {

            TrendingGfycatActivity.start(this)
        }

        bttn_trenting_tags.setOnClickListener {

            TrendingTagsActivity.start(this)
        }
    }
}
