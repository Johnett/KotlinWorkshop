package com.architecture.www.xmlshowcase

import com.google.gson.annotations.SerializedName

    /**
     * UserModel data class
     * */
    data class UserModel(
        @SerializedName("_id")
        var id:String,

        @SerializedName("name")
        var name:String,

        @SerializedName("address")
        var address:String,

        @SerializedName("age")
        var age:String,

        @SerializedName("description")
        var description:String,

        @SerializedName("createdOn")
        var creation:String
        )

    /**
     * UserResponse data class
     * */
    data class UserResponse(
        val results: List<UserModel>
    )