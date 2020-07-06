package com.example.tracking_corona.service

import com.example.tracking_corona.countriesReportUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapService {
    companion object{
        private var api: MapApi? = null

        fun getApi() : MapApi = api ?: synchronized(this){
            api ?: createInstance().also { api = it }
        }

        private fun createInstance() : MapApi{
//            val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor())
//                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(countriesReportUrl)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
                .build()
            return retrofit.create(MapApi::class.java)
        }
    }
}