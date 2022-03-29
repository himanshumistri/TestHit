package com.view.testhit.api

import com.google.gson.JsonObject
import retrofit2.Call

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("token?")
    fun getAccessToken(@Field("grant_type") grantType: String,
                       @Field("client_id") clientId: String,
                       @Field("client_secret") clientSecret: String,
                       @Field("resource") resource: String): Call<JsonObject>
}