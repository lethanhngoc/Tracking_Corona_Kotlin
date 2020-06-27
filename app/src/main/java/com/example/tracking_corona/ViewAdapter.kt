package com.example.tracking_corona

import Fragment.ListDetail
import Fragment.Tracking
import Fragment.Map
import Fragment.info_treatment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewAdapter  (fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment {
        return when (position){
            0->{
                Tracking()
            }
            1->{
                ListDetail()
            }
            2 -> {
                Map()
            }
            else->{
                return info_treatment()
            }
        }
    }

    override fun getCount(): Int {
        return  4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Home"
            1 -> "Detail"
            2 -> "Map"
            else ->{
                return "Map"
            }
        }
    }
}