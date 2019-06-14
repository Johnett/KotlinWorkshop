package com.architecture.www.xmlshowcase

import com.google.gson.annotations.SerializedName

    data class UserModel(
        @SerializedName("_id")
        var id:String? = null,

        @SerializedName("name")
        var name:String? = null,

        @SerializedName("address")
        var address:String? = null,

        @SerializedName("age")
        var age:String? = null,

        @SerializedName("description")
        var description:String? = null,

        @SerializedName("createdOn")
        var creation:String? = null
        )

    data class UserResponse(
        val results: List<UserModel>
    )