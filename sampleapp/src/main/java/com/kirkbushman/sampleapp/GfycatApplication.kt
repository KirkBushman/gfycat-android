package com.kirkbushman.sampleapp

import android.app.Application
import com.kirkbushman.gfycat.auth.*
import com.kirkbushman.gfycat.managers.SharedPrefsStorageManager
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
    lateinit var creds: Credentials
    @Inject
    lateinit var clientCreds: ClientCredentials

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    fun loadClients() {

        val gfycatAuth = GfycatAuthManager(creds, LOGGING)
        val gfycatBearer = gfycatAuth.getAuthToken(SharedPrefsStorageManager(this))
        val gfycatClient = gfycatAuth.getGfycatClient(gfycatBearer)

        Module.setGfycatClient(gfycatClient)

        val redgifsAuth = RedgifsAuthManager(clientCreds, LOGGING)
        val redgifsBearer = redgifsAuth.getAuthToken(SharedPrefsStorageManager(this))
        val redgifsClient = redgifsAuth.getRedgifsClient(redgifsBearer)

        Module.setRedgifsClient(redgifsClient)
    }
}
