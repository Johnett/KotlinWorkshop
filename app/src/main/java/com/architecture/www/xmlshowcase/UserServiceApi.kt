package com.architecture.www.xmlshowcase

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.*

interface UserServiceApi {
    /**
     * Get function
     * */
    @GET("users")
    fun getAllUsers(): Observable<MutableList<UserModel>>

    /**
     * Post function
     * */
    @POST("users")
    fun createUser(@Body jsonObj: JsonObject): Observable<UserModel>

    /**
     * Delete function
     * */
    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id:String): Observable<JsonObject>

    /**
     * Put function
     * */
    @PUT("users/{id}")
    fun updateUser(@Path("id") id:String, @Body jsonObj: JsonObject): Observable<UserModel>

    /**
     * Get function
     * */
    @GET("users/{id}")
    fun getUserById(@Path("id") id:String): Observable<UserModel>
}