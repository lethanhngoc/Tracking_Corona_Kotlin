package com.example.tracking_corona.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class OverallReport : Serializable {
    @SerializedName("cases")
    @Expose
    var cases = 0

    @SerializedName("deaths")
    @Expose
    var deaths = 0

    @SerializedName("recovered")
    @Expose
    var recovered = 0

    override fun toString(): String {
        return "OverallReport{" +
                "cases=" + cases +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                '}'
    }
}