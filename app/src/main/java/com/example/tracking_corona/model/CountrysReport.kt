package com.example.tracking_corona.model

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CountrysReport : Serializable {
    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("cases")
    @Expose
    var cases = 0

    @SerializedName("todayCases")
    @Expose
    var todayCases = 0

    @SerializedName("deaths")
    @Expose
    var deaths = 0

    @SerializedName("todayDeaths")
    @Expose
    var todayDeaths = 0

    @SerializedName("recovered")
    @Expose
    var recovered = 0

    @SerializedName("active")
    @Expose
    var active = 0

    @SerializedName("critical")
    @Expose
    var critical = 0
    var latLng: LatLng? = null

    override fun toString(): String {
        return "CountrysReport{" +
                "country='" + country + '\'' +
                ", cases=" + cases +
                ", todayCases=" + todayCases +
                ", deaths=" + deaths +
                ", todayDeaths=" + todayDeaths +
                ", recovered=" + recovered +
                ", active=" + active +
                ", critical=" + critical +
                ", latLng=" + latLng +
                '}'
    }
}