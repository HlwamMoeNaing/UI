package com.hmn.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hmn.ui.adapter.CommentDetailAdapter
import com.hmn.ui.model.CommentModel

class CommentDetailActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_detail)


        toolbar = findViewById(R.id.back_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        recyclerView = findViewById(R.id.rv_comment)

        val list = ArrayList<CommentModel>()

        list.add(CommentModel("Pujeil", getString(R.string.lorem_ipsum), "11:04", false))

        //  list.add(CommentModel("Lowis", getString(R.string.lorem_ipsum),"8:09",true))

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CommentDetailAdapter(this, list)
    }


}