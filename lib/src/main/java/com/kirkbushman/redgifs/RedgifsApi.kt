package com.kirkbushman.redgifs

import com.kirkbushman.redgifs.models.envelopes.RedgifEnvelope
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.Url

interface RedgifsApi {

    @GET("/v2/gifs/{gifId}")
    fun redgifs(
        @Path("gifId") gifId: String,
        @HeaderMap header: HashMap<String, String>
    ): Call<RedgifEnvelope>
}
