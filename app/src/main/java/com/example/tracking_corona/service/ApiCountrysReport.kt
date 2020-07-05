package com.example.tracking_corona.service

import androidx.fragment.app.FragmentActivity
import com.android.volley.Request
import com.android.volley.RequestQueue.RequestFinishedListener
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.tracking_corona.countriesReportUrl
import com.example.tracking_corona.model.CountrysReport
import com.google.gson.Gson
import java.util.*

abstract class ApiCountrysReport(private val context: FragmentActivity?) {
    private var countrysReportList: List<CountrysReport?>? = null
    abstract fun onComplete(countrysReportList: List<CountrysReport?>?)
    abstract fun onFailure(msg: String?)
    fun startwork() {
        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(
            Request.Method.GET,
            countriesReportUrl,
            Response.Listener { response ->
                val gson = Gson()
                countrysReportList = Arrays.asList(
                    *gson.fromJson(
                        response,
                        Array<CountrysReport>::class.java
                    )
                )
            },
            Response.ErrorListener { error -> onFailure(error.localizedMessage) })
        queue.add(stringRequest)
        queue.addRequestFinishedListener(object : RequestFinishedListener<Any?> {
            override fun onRequestFinished(request: Request<Any?>?) {
                if (countrysReportList != null && countrysReportList!!.size > 0) {
                    onComplete(countrysReportList)
                }
            }
        })
    }

}