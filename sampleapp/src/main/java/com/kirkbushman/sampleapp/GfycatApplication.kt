package com.kirkbushman.sampleapp

import android.app.Application
import com.kirkbushman.gfycat.auth.*
import com.kirkbushman.redgifs.auth.RedgifsAuthManager
import com.kirkbushman.sampleapp.di.Module
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class GfycatApplication : Application() {

    companion object {

        private const val LOGGING = true

        var instance: GfycatApplication? = null
    }

    @Inject
    lateinit var gfycatCreds: Credentials
    @Inject
    lateinit var redgifsCreds: ClientCredentials

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    fun loadClients() {

        val gfycatAuth = GfycatAuthManager(gfycatCreds, LOGGING)
        val gfycatBearer = gfycatAuth.getAuthToken(com.kirkbushman.gfycat.managers.SharedPrefsStorageManager(this))
        val gfycatClient = gfycatAuth.getGfycatClient(gfycatBearer)

        Module.setGfycatClient(gfycatClient)

        val redgifsAuth = RedgifsAuthManager(redgifsCreds, LOGGING)
        val redgifsBearer = redgifsAuth.getAuthToken(com.kirkbushman.redgifs.managers.SharedPrefsStorageManager(this))
        val redgifsClient = redgifsAuth.getRedgifsClient(redgifsBearer)

        Module.setRedgifsClient(redgifsClient)
    }
}
