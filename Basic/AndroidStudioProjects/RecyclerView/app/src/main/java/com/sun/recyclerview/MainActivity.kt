package com.sun.recyclerview

import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdapter(this)

    }

    fun recyclerClick(curData: Data) {
        //리사이클러 안의 내용들을 클릭했을때 실행되는 함수
        Toast.makeText(this, curData.name, Toast.LENGTH_SHORT).show()
    }
}