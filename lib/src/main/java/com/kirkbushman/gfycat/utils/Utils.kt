package com.kirkbushman.gfycat.utils

import android.net.Uri
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Utils {

    const val URL_GFYCAT = "https://api.gfycat.com"
    const val URL_REDGIFS = "https://api.redgifs.com"

    fun buildRetrofit(logging: Boolean): Retrofit {

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
            .baseUrl(URL_GFYCAT)
            .addConverterFactory(moshiFactory)
            .client(httpClient)
            .build()
    }

    fun getGfyIdFromUrl(uri: Uri): String {
        return uri.pathSegments.last()
    }
}
