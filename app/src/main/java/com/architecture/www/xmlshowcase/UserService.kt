package com.architecture.www.xmlshowcase

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//ApiFactory to create TMDB Api
object UserService{

    //OkhttpClient for building http request url
    private val userClient = OkHttpClient().newBuilder()
        .build()



    private fun retrofit() : Retrofit = Retrofit.Builder()
        .client(userClient)
        .baseUrl("https://backender.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    val userServiceApi : UserServiceApi = retrofit().create(UserServiceApi::class.java)

}