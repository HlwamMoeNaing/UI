package com.hmn.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hmn.ui.R
import com.hmn.ui.RecyclerViewClickInterface
import com.hmn.ui.room.CategiryEntity

class PillAdapter(val context: Context,val list:List<CategiryEntity>,val callback:RecyclerViewClickInterface):RecyclerView.Adapter<PillAdapter.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {

        val title = view.findViewById<TextView>(R.id.pill_title)
        val pillTime = view.findViewById<TextView>(R.id.tv_time)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PillAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: PillAdapter.ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.pillTime.text = list[position].time
        holder.itemView.setOnClickListener {
            callback.onItemClick(position,list[position])
        }


    }

    override fun getItemCount(): Int {
       return list.size
    }
}