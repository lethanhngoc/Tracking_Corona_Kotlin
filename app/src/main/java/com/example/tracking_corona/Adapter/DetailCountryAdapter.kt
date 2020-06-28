package com.example.tracking_corona.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tracking_corona.R
import com.example.tracking_corona.model.CityModel
import com.example.tracking_corona.model.CountryModel

class DetailCountryAdapter(val ctx: Context?, var cities: ArrayList<CityModel>) :RecyclerView.Adapter<DetailCountryAdapter.cityVH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cityVH {
        var view :View =LayoutInflater.from(ctx).inflate(R.layout.item_list,parent,false)
        return cityVH(view)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: cityVH, position: Int) {
        val city = cities[position]

        holder.state.text = city.city
        holder.confirmed.text = city.cases.toString()
        holder.active.text = city.beingTreated.toString()
        holder.recovered.text = city.recovered.toString()
        holder.deceased.text = city.deaths.toString()
    }

    class cityVH(itemView : View) : RecyclerView.ViewHolder(itemView){
        val state = itemView.findViewById<TextView>(R.id.stateTv)
        val confirmed = itemView.findViewById<TextView>(R.id.confirmedTv)
        val active = itemView.findViewById<TextView>(R.id.activeTv)
        val recovered = itemView.findViewById<TextView>(R.id.recoveredTv)
        val deceased = itemView.findViewById<TextView>(R.id.deceasedTv)
    }
}