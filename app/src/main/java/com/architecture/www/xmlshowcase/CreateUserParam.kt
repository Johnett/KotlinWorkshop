package com.architecture.www.xmlshowcase

import com.google.gson.annotations.SerializedName

/**
 * Data class to save create user parameter values.
 */
data class CreateUserParam(

    @SerializedName("name")
    var name:String? = null,

    @SerializedName("address")
    var address:String? = null,

    @SerializedName("age")
    var age:String? = null,

    @SerializedName("description")
    var description:String? = null
)