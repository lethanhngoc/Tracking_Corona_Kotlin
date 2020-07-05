package com.example.tracking_corona.model.CountryMap

import com.google.gson.annotations.SerializedName

class CountryLatLong {
    @SerializedName("ref_country_codes")
    var refCountryCodes: List<RefCountryCode>? = null
}