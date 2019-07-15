package com.architecture.www.xmlshowcase

import com.google.gson.annotations.SerializedName

data class ChartData(
    @SerializedName("xaxis")
    var xaxis:String,
    @SerializedName("yaxis")
    var yaxis:String
)