package com.kirkbushman.redgifs

import com.kirkbushman.redgifs.auth.Token
import com.kirkbushman.redgifs.models.envelopes.RedgifEnvelope
import retrofit2.Call
import retrofit2.http.*

interface RedgifsApi {

    @FormUrlEncoded
    @POST("/v2/oauth/client")
    fun getAuthToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Call<Token>

    @GET("/v2/gifs/{gifId}")
    fun redgifs(
        @Path("gifId") gifId: String,
        @HeaderMap header: HashMap<String, String>
    ): Call<RedgifEnvelope>
}
