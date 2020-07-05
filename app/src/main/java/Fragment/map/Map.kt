package Fragment.map

import Fragment.view.InfoAdapterCustom
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tracking_corona.R
import com.example.tracking_corona.databinding.FragmentMapsBinding
import com.example.tracking_corona.model.CountrysReport
import com.example.tracking_corona.network.ApiCountrysReport
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson

class Map: Fragment(), OnMapReadyCallback {
    companion object{
        private val TAG ="MapFragment"
    }
    private lateinit var dashboardViewModel: MapViewModel
    private lateinit var binding:FragmentMapsBinding
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dashboardViewModel = ViewModelProvider(activity!!).get(MapViewModel::class.java)
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_maps, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapView.onCreate(savedInstanceState);
        binding.mapView.onResume()

        try {
            MapsInitializer.initialize(getActivity());
        } catch ( e: GooglePlayServicesNotAvailableException) {

            Toast.makeText(activity, e.localizedMessage, Toast.LENGTH_SHORT).show();
        }
        binding.mapView.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap?) {
//        try {
//            val success: Boolean = googleMap!!.setMapStyle(
//                MapStyleOptions.loadRawResourceStyle(activity,R.raw.mapstyle) )
//            if (!success) {
//                Log.e(TAG, "Style parsing failed.")
//            }
//        } catch (e: Resources.NotFoundException) {
//            Log.e(TAG, "Can't find style. Error: ", e)
//        }

        // Add a marker in Sydney and move the camera
        var hcmus = LatLng(10.76252984,106.68230825)
        if (googleMap != null) {
            googleMap.addMarker(MarkerOptions()
                .position(hcmus)
                .title("Trường Đại Học Khoa Học Tự Nhiên TP HCM"))
        }
        if (googleMap != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 13F))
        }
        loaddData(googleMap)

    }
    fun  loaddData(googleMap: GoogleMap?) {
        val apiCountrysReport: ApiCountrysReport = object : ApiCountrysReport(activity) {
            override fun onComplete(countrysReportList: List<CountrysReport?>?) {


                val list =   activity?.let { CoronaMapUtlis(it).findLatlong(countrysReportList as List<CountrysReport>) }
                if(list !=null){
                    for(cr in list){
                        val marker = MarkerOptions().title(cr.country)
                            .snippet(Gson().toJson(cr))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.circle_image))
                            .position(cr.latLng)
                        googleMap?.addMarker(marker)
                    }
                }
                try {
                    googleMap?.setInfoWindowAdapter(InfoAdapterCustom(layoutInflater))
                }catch (e:IllegalStateException){}
            }
            override fun onFailure(msg: String?) {
                Log.i(TAG, "onFailure: $msg")
            }

        }
        apiCountrysReport.startwork()
    }

}