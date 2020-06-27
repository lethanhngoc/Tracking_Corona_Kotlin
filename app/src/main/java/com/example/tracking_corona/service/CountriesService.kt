package com.example.tracking_corona.service

import com.example.tracking_corona.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {
    companion object{
        private var api: CountriesApi? = null

        fun getApi() : CountriesApi = api ?: synchronized(this){
            api ?: createInstance().also { api = it }
        }

        private fun createInstance() : CountriesApi{
//            val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor())
//                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
                .build()
            return retrofit.create(CountriesApi::class.java)
        }
    }
}