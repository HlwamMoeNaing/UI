package com.hmn.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hmn.ui.model.CommentModel
import com.hmn.ui.R

class CommentDetailAdapter(val context: Context,val list: List<CommentModel>):RecyclerView.Adapter<CommentDetailAdapter.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val commenter = view.findViewById<TextView>(R.id.tv_comment_name)
        val description = view.findViewById<TextView>(R.id.tv_comment_detail)
        val time = view.findViewById<TextView>(R.id.tv_commented_time)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.comment_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.commenter.text = list[position].name
        holder.description.text = list[position].desc
        holder.time.text = list[position].time
    }

    override fun getItemCount(): Int {
    return list.size
    }
}