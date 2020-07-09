package com.example.tracking_corona

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private var positionCountry = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        positionCountry = intent.extras?.getInt("position",0)!!;

        supportActionBar?.setTitle(
            "Chi tiết của " + AffectedCountries.countryModelsList[positionCountry].country
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val pattern : String = "###,###"
        val decimalFormat : DecimalFormat = DecimalFormat(pattern)

        tvCountry.text = AffectedCountries.countryModelsList[positionCountry].country;
        tvCases.text = decimalFormat.format(AffectedCountries.countryModelsList[positionCountry].cases).toString()
        tvRecovered.text = decimalFormat.format(AffectedCountries.countryModelsList[positionCountry].recovered).toString()
        tvCritical.text = decimalFormat.format(AffectedCountries.countryModelsList[positionCountry].critical).toString()
        tvActive.text = decimalFormat.format(AffectedCountries.countryModelsList[positionCountry].active).toString()
        tvTodayCases.text = decimalFormat.format(AffectedCountries.countryModelsList[positionCountry].todayCases).toString()
        tvDeaths.text = decimalFormat.format(AffectedCountries.countryModelsList[positionCountry].deaths).toString()
        tvTodayDeaths.text = decimalFormat.format(AffectedCountries.countryModelsList[positionCountry].todayDeaths).toString()
        tvContinent.text = AffectedCountries.countryModelsList[positionCountry].continent
        tvpopulation.text = decimalFormat.format(AffectedCountries.countryModelsList[positionCountry].population).toString()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}