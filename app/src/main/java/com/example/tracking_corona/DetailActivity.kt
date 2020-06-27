package com.example.tracking_corona

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var positionCountry = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        positionCountry = intent.extras?.getInt("position",0)!!;

        supportActionBar?.setTitle(
            "Details of " + AffectedCountries.countryModelsList[positionCountry].country
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        tvCountry.text = AffectedCountries.countryModelsList[positionCountry].country;
        tvCases.text = AffectedCountries.countryModelsList[positionCountry].cases.toString()
        tvRecovered.text = AffectedCountries.countryModelsList[positionCountry].recovered.toString()
        tvCritical.text = AffectedCountries.countryModelsList[positionCountry].critical.toString()
        tvActive.text = AffectedCountries.countryModelsList[positionCountry].active.toString()
        tvTodayCases.text = AffectedCountries.countryModelsList[positionCountry].todayCases.toString()
        tvDeaths.text = AffectedCountries.countryModelsList[positionCountry].deaths.toString()
        tvTodayDeaths.text = AffectedCountries.countryModelsList[positionCountry].todayDeaths.toString()

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}