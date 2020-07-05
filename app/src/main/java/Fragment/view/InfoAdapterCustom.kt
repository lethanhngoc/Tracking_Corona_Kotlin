package Fragment.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.tracking_corona.R
import com.example.tracking_corona.model.OverallReport
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.gson.Gson

class InfoAdapterCustom( val inflater: LayoutInflater) : GoogleMap.InfoWindowAdapter {
    private lateinit var popup: View


    override fun getInfoWindow(marker: Marker?): View? {
        return null
    }

    @SuppressLint("InflateParams")
    override fun getInfoContents(marker: Marker): View? {
        popup = inflater.inflate(R.layout.infowindow,null)
        val country: TextView =  popup.findViewById(R.id.title)
        val recoverd: TextView =  popup.findViewById(R.id.recoverd)
        val total: TextView =  popup.findViewById(R.id.total)
        val deaths: TextView =  popup.findViewById(R.id.deaths)
        country.text = marker.title
        val obj =  Gson().fromJson(marker.snippet, OverallReport::class.java)
        recoverd.text = "Hồi phục: ${obj.recovered}"
        total.text = "Tổng số ca nhiễm: ${obj.cases}"
        deaths.text = "Chết: ${obj.deaths}"

        return popup
    }
}