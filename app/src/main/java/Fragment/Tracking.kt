package Fragment

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import com.example.tracking_corona.AffectedCountries
import com.example.tracking_corona.MainActivity
import com.example.tracking_corona.R
import com.example.tracking_corona.service.CountriesService
import kotlinx.android.synthetic.main.fragment_tracking.*
import kotlinx.coroutines.*
import org.eazegraph.lib.models.PieModel

class Tracking: Fragment() {

    lateinit var btn_Track : Button
    lateinit var ACTIVITY : MainActivity
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
    lateinit var scope: CoroutineScope;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_Track = view?.findViewById<Button>(R.id.btnTrack)
        btn_Track.setOnClickListener{
            val intent = Intent(activity, AffectedCountries::class.java)
            startActivity(intent)
        }

        scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            Log.e(TAG, Thread.currentThread().toString())
            val allResp = withContext(Dispatchers.IO) {
                CountriesService.getApi().getAllCountries()
            }

            val allYesterdayResp = withContext(Dispatchers.IO) {
                CountriesService.getApi().getAllCountriesYesterday()
            }
            val vnResp = withContext(Dispatchers.IO) {
                CountriesService.getApi().getCountryVietNam()
            }
            val vnYesterdayResp = withContext(Dispatchers.IO) {
                CountriesService.getApi().getCountryVietNamYesterday()
            }
            tvaffected.text = vnResp.cases.toString();
            tvdeath.text = vnResp.deaths.toString();
            tvrecovered.text = vnResp.recovered.toString();
            tvactive.text = vnResp.active.toString();
            tvserious.text = vnResp.critical.toString();
            tvaffectedDaily.text = vnResp.todayCases.toString()
            tvdeathDaily.text = vnResp.todayDeaths.toString()
            tvrecoveredDaily.text = vnResp.todayRecovered.toString()
            tvactiveDaily.text = (vnResp.active - vnYesterdayResp.active).toString()
            tvseriousDaily.text = (vnResp.critical - vnYesterdayResp.critical).toString()


            piechart.addPieSlice(
                PieModel("Recovered", vnResp.recovered.toFloat(),Color.parseColor("#66BB6A"))
            )

            piechart.addPieSlice(
                PieModel("Active", vnResp.active.toFloat(),Color.parseColor("#4CB5FF"))
            )
            piechart.addPieSlice(
                PieModel("Deaths", vnResp.deaths.toFloat(),Color.parseColor("#EF5350"))
            )
            piechart.addPieSlice(
                PieModel("Critical", vnResp.critical.toFloat(),Color.parseColor("#8359FF"))
            )

            piechart.startAnimation();
            toggleAlarm.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    tvaffected.text = allResp.cases.toString();
                    tvdeath.text = allResp.deaths.toString();
                    tvrecovered.text = allResp.recovered.toString();
                    tvactive.text = allResp.active.toString();
                    tvserious.text = allResp.critical.toString();

                    tvaffectedDaily.text = allResp.todayCases.toString()
                    tvdeathDaily.text = allResp.todayDeaths.toString()
                    tvrecoveredDaily.text = allResp.todayRecovered.toString()
                    tvactiveDaily.text = (allResp.active - allYesterdayResp.active).toString()
                    tvseriousDaily.text = (allResp.critical - allYesterdayResp.critical).toString()

                    piechart.clearChart()
                    piechart.addPieSlice(
                        PieModel("Recovered", allResp.recovered.toFloat(),Color.parseColor("#66BB6A"))
                    )

                    piechart.addPieSlice(
                        PieModel("Active", allResp.active.toFloat(),Color.parseColor("#4CB5FF"))
                    )
                    piechart.addPieSlice(
                        PieModel("Deaths", allResp.deaths.toFloat(),Color.parseColor("#EF5350"))
                    )
                    piechart.addPieSlice(
                        PieModel("Critical", allResp.critical.toFloat(),Color.parseColor("#8359FF"))
                    )

                    piechart.startAnimation();
                } else {
                    tvaffected.text = vnResp.cases.toString();
                    tvdeath.text = vnResp.deaths.toString();
                    tvrecovered.text = vnResp.recovered.toString();
                    tvactive.text = vnResp.active.toString();
                    tvserious.text = vnResp.critical.toString();

                    tvaffectedDaily.text = vnResp.todayCases.toString()
                    tvdeathDaily.text = vnResp.todayDeaths.toString()
                    tvrecoveredDaily.text = vnResp.todayRecovered.toString()
                    tvactiveDaily.text = (vnResp.active - vnYesterdayResp.active).toString()
                    tvseriousDaily.text = (vnResp.critical - vnYesterdayResp.critical).toString()

                    piechart.clearChart()
                    piechart.addPieSlice(
                        PieModel("Recovered", vnResp.recovered.toFloat(),Color.parseColor("#66BB6A"))
                    )

                    piechart.addPieSlice(
                        PieModel("Active", vnResp.active.toFloat(),Color.parseColor("#4CB5FF"))
                    )
                    piechart.addPieSlice(
                        PieModel("Deaths", vnResp.deaths.toFloat(),Color.parseColor("#EF5350"))
                    )
                    piechart.addPieSlice(
                        PieModel("Critical", vnResp.critical.toFloat(),Color.parseColor("#8359FF"))
                    )

                    piechart.startAnimation();
                }
            })
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tracking, container, false)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        ACTIVITY = context as MainActivity
    }
    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}