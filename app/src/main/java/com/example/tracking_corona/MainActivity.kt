package com.example.tracking_corona

import Fragment.ListDetail
import Fragment.Map
import Fragment.Tracking
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var bottomNavigationView: BottomNavigationView
    var currentFragment : Fragment = Tracking()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        viewPager = findViewById(R.id.viewPager)

        val fragmentAdapter  =
            ViewAdapter(supportFragmentManager)
        viewPager.adapter= fragmentAdapter
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true
            }

        })
        bottomNavigationView =findViewById(R.id.navBottom)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId){
                R.id.item_home ->{
                    viewPager.currentItem =0
                    replaceFragment(Tracking())
                    currentFragment = Tracking()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.item_list ->{
                    viewPager.currentItem =1
                    replaceFragment(ListDetail())
                    currentFragment = ListDetail()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.item_map ->{
                    viewPager.currentItem =2
                    replaceFragment(Map())
                    currentFragment = Map()
                    return@setOnNavigationItemSelectedListener true
                }
                else ->{
                    viewPager.currentItem =3
                    replaceFragment(Map())
                    currentFragment = Map()
                    return@setOnNavigationItemSelectedListener true
                }
            }

        }
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction =supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()
    }


}
