package com.kirkbushman.gfycat

import com.kirkbushman.gfycat.auth.Token
import com.kirkbushman.gfycat.models.Gfycat
import com.kirkbushman.gfycat.models.User
import retrofit2.Call
import retrofit2.http.*

interface GfycatApi {

    @FormUrlEncoded
    @POST("/v1/oauth/token")
    fun getAuthToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String = "client_credentials"
    ): Call<Token>

    @GET("/me")
    fun me(
        @HeaderMap header: HashMap<String, String>
    ): Call<User>

    @GET("/v1/gfycats/{gfyid}")
    fun gfycat(
        @Path("gfyid") gfyId: String,
        @HeaderMap header: HashMap<String, String>
    ): Call<Gfycat>
}
