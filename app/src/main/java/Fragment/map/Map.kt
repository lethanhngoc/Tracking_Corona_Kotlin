package Fragment.map

import Fragment.Tracking
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
import com.example.tracking_corona.service.ApiCountrysReport
import com.example.tracking_corona.service.CountriesService
import com.example.tracking_corona.service.MapService
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import kotlinx.coroutines.*

class Map: Fragment(), OnMapReadyCallback {
    companion object{
        private val TAG ="MapFragment"
    }
    private lateinit var dashboardViewModel: MapViewModel
    private lateinit var binding:FragmentMapsBinding
    private lateinit var scope: CoroutineScope;
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
        scope = CoroutineScope(Dispatchers.Main)
        try {
            MapsInitializer.initialize(getActivity());
        } catch ( e: GooglePlayServicesNotAvailableException) {

            Toast.makeText(activity, e.localizedMessage, Toast.LENGTH_SHORT).show();
        }
        binding.mapView.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap?) {
        // Add a marker in Sydney and move the camera
        var hcmus = LatLng(10.76252984,106.68230825)
        if (googleMap != null) {
            googleMap.addMarker(MarkerOptions()
                .position(hcmus)
                .title("Trường Đại Học Khoa Học Tự Nhiên TP HCM"))
        }
        if (googleMap != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 7F))
        }
        loaddData(googleMap)

    }
    fun  loaddData(googleMap: GoogleMap?) {
        scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            val allResp = withContext(Dispatchers.IO) {
                MapService.getApi().getAll()
            }
                val list = activity?.let { CoronaMapUtlis(it).findLatlong(allResp) }
                if(list !=null){
                    for(cr in list){
                        val marker = cr.latLng?.let {
                            MarkerOptions().title(cr.country)
                                .snippet(Gson().toJson(cr))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.circle_image))
                                .position(it)
                        }
                        googleMap?.addMarker(marker)
                    }
                }
                try {
                    googleMap?.setInfoWindowAdapter(InfoAdapterCustom(layoutInflater))
                }catch (e:IllegalStateException){}
            }
    }
    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}