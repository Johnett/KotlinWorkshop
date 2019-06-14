package com.architecture.www.xmlshowcase

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserServiceApi {
    @GET("users")
    fun getAllUsers(): Observable<List<UserModel>>

    @POST("users")
    fun createUser(@Body jsonObj: JsonObject): Observable<UserModel>
}