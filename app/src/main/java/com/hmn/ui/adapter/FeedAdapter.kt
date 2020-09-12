package com.hmn.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.hmn.ui.CommentDetailActivity
import com.hmn.ui.model.FeedModel
import com.hmn.ui.R
import de.hdodenhof.circleimageview.CircleImageView

class FeedAdapter(val context: Context,val list:ArrayList<FeedModel>):RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val circleImage = view.findViewById<CircleImageView>(R.id.circle_image)
        val name = view.findViewById<TextView>(R.id.profile_name)
        var date = view.findViewById<TextView>(R.id.tv_date_time)
        var post = view.findViewById<TextView>(R.id.tv_post)
        var postImage = view.findViewById<AppCompatImageView>(R.id.img_news)
        var postTitle = view.findViewById<TextView>(R.id.tv_news_title)
        var postDesc =  view.findViewById<TextView>(R.id.tv_post_desc)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(
           LayoutInflater.from(context).inflate(R.layout.newfeed_design,parent,false)
       )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.circleImage.setImageResource(list[position].circleImage)
        holder.name.text = list[position].name
        holder.date.text = list[position].dateTime
        holder.post.text = list[position].post
        holder.postImage.setImageResource(list[position].postImg)
        holder.postTitle.text = list[position].postTitle
        holder.postDesc.text = list[position].postDesc

        holder.itemView.setOnClickListener {
            val i = Intent(context, CommentDetailActivity::class.java)
            context.startActivity(i)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}