package com.architecture.www.xmlshowcase

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.*

interface UserServiceApi {
    @GET("users")
    fun getAllUsers(): Observable<MutableList<UserModel>>

    @POST("users")
    fun createUser(@Body jsonObj: JsonObject): Observable<UserModel>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id:String): Observable<JsonObject>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id:String, @Body jsonObj: JsonObject): Observable<UserModel>

    @GET("users/{id}")
    fun getUserById(@Path("id") id:String): Observable<UserModel>
}