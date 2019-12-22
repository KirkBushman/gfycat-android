package com.kirkbushman.sampleapp

import android.app.Application
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.auth.AuthManager
import com.kirkbushman.gfycat.auth.Credentials
import com.kirkbushman.gfycat.auth.TokenBearer
import com.kirkbushman.gfycat.managers.SharedPrefsStorageManager
import org.xmlpull.v1.XmlPullParser

class GfycatApplication : Application() {

    companion object {

        private const val LOGGING = true

        var instance: GfycatApplication? = null
    }

    private var bearer: TokenBearer? = null
    private var client: GfycatClient? = null

    fun loadClient() {

        val creds = loadCredsFromXmlFile()
        val auth = AuthManager(creds, LOGGING)

        bearer = auth.getAuthToken(SharedPrefsStorageManager(this))
        client = auth.getGfycatClient(bearer!!)
    }

    fun getTokenBearer(): TokenBearer? {
        return bearer
    }

    fun getClient(): GfycatClient? {
        return client
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    private fun loadCredsFromXmlFile(): Credentials {
        val xpp = resources.getXml(R.xml.credentials)

        var clientId = ""
        var clientSecret = ""

        while (xpp.eventType != XmlPullParser.END_DOCUMENT) {

            when (xpp.eventType) {

                XmlPullParser.START_TAG -> {

                    when (xpp.name) {
                        "clientId" -> clientId = xpp.nextText()
                        "clientSecret" -> clientSecret = xpp.nextText()
                    }
                }
            }

            xpp.next()
        }

        return Credentials(clientId, clientSecret)
    }
}