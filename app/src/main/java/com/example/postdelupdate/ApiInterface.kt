package com.example.postdelupdate

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


//https://dojo-recipes.herokuapp.com/test/

interface ApiInterface {

    @GET("test/")

    suspend fun getData(): Response<List<Data>>

    @POST("test/")
    fun postData(@Body data: Data): Call<Data>

    @PUT("test/{id}")
    fun update(@Path(
        "id") id: String,
               @Body data: Data
    ): Call<Data>

    @DELETE("/test/{id}")
    fun deleteUser(@Path("id") id: String): Call<Void>
}