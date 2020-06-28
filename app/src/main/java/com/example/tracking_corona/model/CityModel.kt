package com.example.tracking_corona.model


import com.google.gson.annotations.SerializedName

data class CityModel(
    @SerializedName("beingTreated")
    val beingTreated: Int,
    @SerializedName("cases")
    val cases: Int,
    @SerializedName("city")
    val city: String,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("updated")
    val updated: Long
)