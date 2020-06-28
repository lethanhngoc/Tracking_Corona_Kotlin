package com.example.tracking_corona.service

import com.example.tracking_corona.model.CityModel
import com.example.tracking_corona.model.Countries
import com.example.tracking_corona.model.CountryModel
import retrofit2.http.GET

interface CountriesApi {
    @GET("all")
    suspend fun getAllCountries(): Countries

    @GET("all?yesterday=true")
    suspend fun getAllCountriesYesterday(): Countries

    @GET("countries/VietNam")
    suspend fun getCountryVietNam(): CountryModel

    @GET("countries/VietNam?yesterday=true")
    suspend fun getCountryVietNamYesterday(): CountryModel

    @GET("countries")
    suspend fun getCountries(): ArrayList<CountryModel>

    @GET("gov/VietNam")
    suspend fun getDetailVietNam(): ArrayList<CityModel>
}