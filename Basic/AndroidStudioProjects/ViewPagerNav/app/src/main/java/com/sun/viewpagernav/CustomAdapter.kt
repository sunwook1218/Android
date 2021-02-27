package com.sun.viewpagernav

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val profile = v.findViewById<ImageView>(R.id.imageView)
    val name = v.findViewById<TextView>(R.id.textView)
}

class CustomAdapter(val dataList: ArrayList<Data> ,val context: Context) :
    RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val cellForRow =
            LayoutInflater.from(context).inflate(R.layout.recommend_list, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        var curData = dataList[position]
        holder.profile.setImageResource(curData.profile)
        holder.name.text = curData.name

        holder.itemView.setOnClickListener {

        }
    }


}