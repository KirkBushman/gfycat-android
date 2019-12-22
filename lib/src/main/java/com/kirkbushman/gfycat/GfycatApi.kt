package com.kirkbushman.gfycat

import com.kirkbushman.gfycat.auth.Token
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.gfycat.models.User
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
    ): Call<User>

    @GET("/v1/gfycats/{gfyid}")
    fun gfycat(
        @Path("gfyid") gfyId: String,
        @HeaderMap header: HashMap<String, String>
    ): Call<Gfycat>

    @GET("/v1/gfycats/trending")
    fun trendingGfycat(
        @Query("tagName") tagName: String? = null,
        @Query("count") count: Int? = null,
        @Query("cursor") cursor: String? = null,
        @HeaderMap header: HashMap<String, String>
    ): Call<GfycatsEnvelope>
}
