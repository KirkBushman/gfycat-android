package com.kirkbushman.gfycat

import com.kirkbushman.gfycat.auth.Token
import com.kirkbushman.gfycat.models.*
import com.kirkbushman.gfycat.models.Tag
import com.kirkbushman.gfycat.models.envelopes.GfycatEnvelope
import com.kirkbushman.gfycat.models.envelopes.GfycatsEnvelope
import com.kirkbushman.gfycat.models.http.AuthBodyClient
import com.kirkbushman.gfycat.models.http.AuthBodyPassword
import com.kirkbushman.gfycat.models.http.AuthBodyRenew
import com.kirkbushman.gfycat.utils.Utils.URL_GFYCAT
import retrofit2.Call
import retrofit2.http.*

interface GfycatApi {

    @POST("/v1/oauth/token")
    fun getAuthToken(@Body request: AuthBodyClient): Call<Token>

    @POST("/v1/oauth/token")
    fun getAuthToken(@Body request: AuthBodyPassword): Call<Token>

    @POST("/v1/oauth/token")
    fun refreshToken(@Body request: AuthBodyRenew): Call<Token>

    @GET("/v1/me")
    fun me(
        @HeaderMap header: HashMap<String, String>
    ): Call<Me>

    @GET("/v1/me/follows")
    fun following(
        @HeaderMap header: HashMap<String, String>
    ): Call<Following>

    @GET("/v1/me/followers")
    fun followers(
        @HeaderMap header: HashMap<String, String>
    ): Call<Followers>

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

    @GET
    fun gfycat(
        @Url url: String,
        @HeaderMap header: HashMap<String, String>
    ): Call<GfycatEnvelope>

    @GET
    fun redgifs(
        @Url url: String,
        @HeaderMap header: HashMap<String, String>
    ): Call<GfycatEnvelope>

    @GET("/v1/stickers")
    fun stickers(
        @Query("count") count: Int? = null,
        @Query("cursor") cursor: String? = null,
        @HeaderMap header: HashMap<String, String>
    ): Call<GfycatsEnvelope>

    @GET("/v1/stickers/search")
    fun stickersSearch(
        @Query("search_text") searchText: String,
        @Query("count") count: Int? = null,
        @Query("cursor") cursor: String? = null,
        @HeaderMap header: HashMap<String, String>
    ): Call<GfycatsEnvelope>

    @GET("/v1/reactions/populated")
    fun reactionGfycats(
        @Query("gfyCount") gfyCount: Int? = null,
        @Query("locale") locale: String? = null,
        @Query("cursor") cursor: String? = null,
        @HeaderMap header: HashMap<String, String>
    ): Call<ReactionTags>

    @GET("/v1/reactions/populated")
    fun reactionGfycat(
        @Query("tagName") tagName: String,
        @Query("gfyCount") gfyCount: Int? = null,
        @Query("locale") locale: String? = null,
        @Query("cursor") cursor: String? = null,
        @HeaderMap header: HashMap<String, String>
    ): Call<Tag>

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
