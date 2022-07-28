package com.example.Scanner

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("QR/register")
    fun postRegister(
        @Field("nim") nim:String,
        @Field("nama") nama: String,
        @Field("prodi") prodi: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<Response>

    @GET("QR/login")
    fun getLogin(
        @Query("nim") nim: String,
        @Query("password") password: String
    ): Call<Response>

    @FormUrlEncoded
    @POST("QR/token")
    fun postScan(
        @Field("nim") nim: String,
        @Field("token") token: String
    ): Call<Response>

}