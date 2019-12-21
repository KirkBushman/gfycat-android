package com.kirkbushman.gfycat.utils

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Utils {

    private const val BASE_URL = "https://api.gfycat.com"

    fun getRetrofit(logging: Boolean): Retrofit {

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
            .baseUrl(BASE_URL)
            .addConverterFactory(moshiFactory)
            .client(httpClient)
            .build()
    }
}
