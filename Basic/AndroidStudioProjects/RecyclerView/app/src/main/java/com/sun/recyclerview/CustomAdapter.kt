package com.sun.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class Data(val profile: Int, val name: String)

class CustomViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val profile = v.findViewById<ImageView>(R.id.imageView)
    val name = v.findViewById<TextView>(R.id.textView)
}

class CustomAdapter(val context: Context) :
    RecyclerView.Adapter<CustomViewHolder>() {

    val DATA_LIST = arrayListOf(
        Data(R.drawable.img, "img의 작품 보기"),
        Data(R.drawable.rosuuri, "rosuuri의 작품 보기"),
        Data(R.drawable.ominaesi, "おみなえし의 작품 보기"),
        Data(R.drawable.kaou, "がおう의 작품 보기"),
        Data(R.drawable.kitere, "kitere의 작품 보기"),
        Data(R.drawable.snatti, "snatti의 작품 보기"),
        Data(R.drawable.custom, "custom의 작품 보기"),
        Data(R.drawable.kidmo, "kidmo의 작품 보기")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val cellForRow =
            LayoutInflater.from(context).inflate(R.layout.custom_list, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount() = DATA_LIST.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        var curData = DATA_LIST[position]
        holder.profile.setImageResource(curData.profile)
        holder.name.text = curData.name

        holder.itemView.setOnClickListener {
            (context as MainActivity).recyclerClick(curData)
        }
    }


}