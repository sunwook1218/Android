package com.sun.viewpagernav

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.sun.viewpagernav.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var viewList = ArrayList<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*var service = ServiceImpl()
        var dataList = service.getDataList()

        var recyclerV = findViewById<RecyclerView>(R.id.recommend_rv)

        recyclerV.layoutManager = LinearLayoutManager(this)
        recyclerV.adapter = CustomAdapter(dataList, this)*/

        viewList.add(layoutInflater.inflate(R.layout.fragment_recommend, null))
        viewList.add(layoutInflater.inflate(R.layout.fragment_chat, null))
        viewList.add(layoutInflater.inflate(R.layout.fragment_alert, null))
        viewList.add(layoutInflater.inflate(R.layout.fragment_myinfo, null))

        binding.mainVp.adapter = pagerAdapter()

        binding.mainVp.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.bottomNavigationView.selectedItemId = R.id.nav_recommend
                    1 -> binding.bottomNavigationView.selectedItemId = R.id.nav_chat
                    2 -> binding.bottomNavigationView.selectedItemId = R.id.nav_alert
                    3 -> binding.bottomNavigationView.selectedItemId = R.id.nav_myinfo
                }
            }
        })

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_recommend -> binding.mainVp.setCurrentItem(0)
                R.id.nav_chat -> binding.mainVp.setCurrentItem(1)
                R.id.nav_alert -> binding.mainVp.setCurrentItem(2)
                R.id.nav_myinfo -> binding.mainVp.setCurrentItem(3)
            }

            return@setOnNavigationItemSelectedListener true
        }

    }

    inner class pagerAdapter : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any) = view == `object`

        override fun getCount() = viewList.size

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            var curView = viewList[position]
            binding.mainVp.addView(curView)
            return curView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            binding.mainVp.removeView(`object` as View)
        }
    }
}