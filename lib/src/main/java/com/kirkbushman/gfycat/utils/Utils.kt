package com.kirkbushman.gfycat.utils

import android.net.Uri
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Utils {

    private const val URL_GFYCAT = "https://api.gfycat.com"
    private const val URL_REDGIFS = "https://api.redgifs.com"

    fun buildGfycatRetrofit(logging: Boolean): Retrofit {

        return buildRetrofit(URL_GFYCAT, logging)
    }

    fun buildRedgifsRetrofit(logging: Boolean): Retrofit {

        return buildRetrofit(URL_REDGIFS, logging)
    }

    private fun buildRetrofit(url: String, logging: Boolean): Retrofit {

        val moshi = Moshi.Builder().build()
        val moshiFactory = MoshiConverterFactory.create(moshi)

        val httpClient = if (logging) {

            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            OkHttpClient
                .Builder()
                .addInterceptor(logger)
                .build()
        } else {

            OkHttpClient
                .Builder()
                .build()
        }

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(moshiFactory)
            .client(httpClient)
            .build()
    }

    fun getGfyIdFromUrl(uri: Uri): String {
        return uri.pathSegments.last()
    }
}
