package com.example.tracking_corona

import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_tracking.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_tracking)

        var url: String = "https://disease.sh/v2/countries/VietNam";
        fetchData(url)
        toggleAlarm.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Log.d("alarmCheck", "ALARM SET TO TRUE")
                url = "https://disease.sh/v2/all";
                fetchData(url)
            } else {
                Log.d("alarmCheck", "ALARM SET TO FALSE")
                url = "https://disease.sh/v2/countries/VietNam";
                fetchData(url)
            }
        })

    }

    private fun fetchData(url : String){
        val request = StringRequest(
            Request.Method.GET, url, Response.Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    tvaffected.text = jsonObject.getString("cases") ;
                    tvdeath.text = jsonObject.getString("deaths")
                    tvrecovered.text = jsonObject.getString("recovered")
                    tvactive.text = jsonObject.getString("active")
                    tvserious.text = jsonObject.getString("critical")
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    }
}
