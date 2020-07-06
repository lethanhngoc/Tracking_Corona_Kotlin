package com.example.tracking_corona.service

import com.example.tracking_corona.model.CityModel
import com.example.tracking_corona.model.CountrysReport

import retrofit2.http.GET

interface MapApi {
    @GET("countries")
    suspend fun getAll(): List<CountrysReport>
}