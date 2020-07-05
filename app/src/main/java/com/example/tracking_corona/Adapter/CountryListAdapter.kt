package com.example.tracking_corona.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tracking_corona.R
import com.example.tracking_corona.databinding.RowCountryListBinding
import com.example.tracking_corona.model.CountrysReport

class CountryListAdapter(val context: Context, val list:List<CountrysReport>) : RecyclerView.Adapter<CountryListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding:RowCountryListBinding = DataBindingUtil.inflate(inflater, R.layout.row_country_list,parent,false)
        return CountryListHolder(binding)

    }



    override fun onBindViewHolder(holder: CountryListHolder, position: Int) {
        val item = list.get(position)
        holder.binding.report = item
    }
    override fun getItemCount(): Int {

        return  list.size
    }
}
class CountryListHolder(val binding: RowCountryListBinding) : RecyclerView.ViewHolder(binding.root) {

}