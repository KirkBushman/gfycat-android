package com.kirkbushman.gfycat

import com.kirkbushman.gfycat.auth.Token
import com.kirkbushman.gfycat.models.Me
import com.kirkbushman.gfycat.models.User
import com.kirkbushman.gfycat.models.envelopes.GfycatEnvelope
import com.kirkbushman.gfycat.models.envelopes.GfycatsEnvelope
import com.kirkbushman.gfycat.models.http.AuthRequestBody
import retrofit2.Call
import retrofit2.http.*

interface GfycatApi {

    @POST("/v1/oauth/token")
    fun getAuthToken(@Body request: AuthRequestBody): Call<Token>

    @GET("/me")
    fun me(
        @HeaderMap header: HashMap<String, String>
    ): Call<Me>

    @GET("/v1/users/{userId}")
    fun user(
        @Path("userId") userId: String,
        @HeaderMap header: HashMap<String, String>
    ): Call<User>

    @GET("/v1/users/{userId}/gfycats")
    fun userFeed(
        @Path("userId") userId: String,
        @Query("count") count: Int? = null,
        @Query("cursor") cursor: String? = null,
        @HeaderMap header: HashMap<String, String>
    ): Call<GfycatsEnvelope>

    @GET("/v1/gfycats/{gfyid}")
    fun gfycat(
        @Path("gfyid") gfyId: String,
        @HeaderMap header: HashMap<String, String>
    ): Call<GfycatEnvelope>

    @GET("/v1/gfycats/trending")
    fun trendingGfycat(
        @Query("tagName") tagName: String? = null,
        @Query("count") count: Int? = null,
        @Query("cursor") cursor: String? = null,
        @HeaderMap header: HashMap<String, String>
    ): Call<GfycatsEnvelope>

    @GET("/v1/tags/trending")
    fun trendingTags(
        @Query("tagCount") tagCount: Int? = null,
        @Query("gfyCount") gfyCount: Int? = null,
        @Query("cursor") cursor: String? = null,
        @HeaderMap header: HashMap<String, String>
    ): Call<List<String>>
}
