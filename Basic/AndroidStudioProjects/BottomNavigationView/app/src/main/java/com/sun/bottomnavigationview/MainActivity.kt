package com.sun.bottomnavigationview

import android.graphics.ColorSpace
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.sun.bottomnavigationview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var binding : ActivityMainBinding? = null
    var viewList = ArrayList<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        viewList.add(layoutInflater.inflate(R.layout.fragment_home, null))
        viewList.add(layoutInflater.inflate(R.layout.fragment_favorite, null))
        viewList.add(layoutInflater.inflate(R.layout.fragment_profile, null))

        binding!!.viewPager2.adapter

    }
}