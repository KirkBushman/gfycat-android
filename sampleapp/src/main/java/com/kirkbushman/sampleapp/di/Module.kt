package com.kirkbushman.sampleapp.di

import android.content.Context
import com.kirkbushman.gfycat.GfycatClient
import com.kirkbushman.gfycat.auth.ClientCredentials
import com.kirkbushman.gfycat.auth.Credentials
import com.kirkbushman.gfycat.auth.PasswordCredentials
import com.kirkbushman.redgifs.RedgifsClient
import com.kirkbushman.sampleapp.R
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.xmlpull.v1.XmlPullParser
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Reusable
    fun provideCredentials(

        @ApplicationContext context: Context

    ): Credentials {

        val xpp = context.resources.getXml(R.xml.credentials)

        var usePasswordAuth = true
        var clientId = ""
        var clientSecret = ""
        var username = ""
        var password = ""

        while (xpp.eventType != XmlPullParser.END_DOCUMENT) {

            when (xpp.eventType) {

                XmlPullParser.START_TAG -> {

                    when (xpp.name) {
                        "usePasswordAuth" -> usePasswordAuth = xpp.nextText()!!.toBoolean()
                        "clientId" -> clientId = xpp.nextText()
                        "clientSecret" -> clientSecret = xpp.nextText()
                        "username" -> username = xpp.nextText()
                        "password" -> password = xpp.nextText()
                    }
                }
            }

            xpp.next()
        }

        return if (usePasswordAuth) {
            PasswordCredentials(clientId, clientSecret, username, password)
        } else {
            ClientCredentials(clientId, clientSecret)
        }
    }

    @Provides
    @Reusable
    fun provideRedgifsCredentials(

        @ApplicationContext context: Context

    ): ClientCredentials {

        val xpp = context.resources.getXml(R.xml.credentials)

        var clientId = ""
        var clientSecret = ""

        while (xpp.eventType != XmlPullParser.END_DOCUMENT) {

            when (xpp.eventType) {

                XmlPullParser.START_TAG -> {

                    when (xpp.name) {
                        "redClientId" -> clientId = xpp.nextText()
                        "redClientSecret" -> clientSecret = xpp.nextText()
                    }
                }
            }

            xpp.next()
        }

        return ClientCredentials(clientId, clientSecret)
    }

    private var gfycatClient: GfycatClient? = null

    fun setGfycatClient(client: GfycatClient) {
        this.gfycatClient = client
    }

    @Provides
    @Singleton
    fun provideGfycatClient(): GfycatClient {

        return gfycatClient ?: throw IllegalStateException("Gfycat client is null!")
    }

    private var redgifsClient: RedgifsClient? = null

    fun setRedgifsClient(client: RedgifsClient) {
        this.redgifsClient = client
    }

    @Provides
    @Singleton
    fun provideRedgifsClient(): RedgifsClient {

        return redgifsClient ?: throw IllegalStateException("Redgifs client is null!")
    }
}
