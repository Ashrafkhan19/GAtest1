package com.mapkin.gatest

import com.mapkin.gatest.api.ImageList
import com.mapkin.gatest.api.UserList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface UserInterface {

    @GET("users")
    fun getUserList(): Call<UserList>

    @GET("photos")
    fun getUserImg(@Query("id")id: Int): Call<ImageList>

    companion object{
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        fun create(): UserInterface {

            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()

            return retrofit.create(UserInterface::class.java)
        }
    }
}