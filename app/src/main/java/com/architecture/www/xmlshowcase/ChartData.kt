package com.architecture.www.xmlshowcase

import com.google.gson.annotations.SerializedName

/**
 * Chart data class to store x and y axis values
 */
data class ChartData(
    @SerializedName("xaxis")
    var xaxis:String,
    @SerializedName("yaxis")
    var yaxis:String
)