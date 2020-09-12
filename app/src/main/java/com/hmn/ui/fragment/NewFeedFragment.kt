package com.hmn.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hmn.ui.MainActivity
import com.hmn.ui.adapter.FeedAdapter
import com.hmn.ui.model.FeedModel
import com.hmn.ui.R
import kotlinx.android.synthetic.main.fragment_new_feed.*


class NewFeedFragment : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.back_toolbar)
        toolbar.setNavigationIcon(R.drawable.back_24_red)
        toolbar.title = "Downloaded Songs"
        toolbar.setNavigationOnClickListener {
            val activity = activity as MainActivity
            activity.onSupportNavigateUp()
        }




        val arraylist = ArrayList<FeedModel>()
        arraylist.add(
            FeedModel(
                R.drawable.david_backham,
                "David Backham",
                "23/06/20, 04:37",
                getString(R.string.lorem_ipsum),
                R.drawable.one,
                "First Post",
                getString(R.string.lorem_ipsum)
            )
        )


        arraylist.add(
            FeedModel(
                R.drawable.jenny,
                "Jenny",
                "23/06/20, 04:37",
                getString(R.string.lorem_ipsum),
                R.drawable.two,
                "Second Post",
                getString(R.string.lorem_ipsum)
            )
        )

        arraylist.add(
            FeedModel(
                R.drawable.kin_jone_an,
                "Konhjone",
                "23/06/20, 04:37",
                getString(R.string.lorem_ipsum),
                R.drawable.three,
                "Third Post",
                getString(R.string.lorem_ipsum)
            )
        )



        arraylist.add(
            FeedModel(
                R.drawable.hritik_roshan,
                "Roshan",
                "23/06/20, 04:37",
                getString(R.string.lorem_ipsum),
                R.drawable.four,
                "Four Post",
                getString(R.string.lorem_ipsum)
            )
        )


        arraylist.add(
            FeedModel(
                R.drawable.five,
                "Roshan",
                "23/06/20, 04:37",
                getString(R.string.lorem_ipsum),
                R.drawable.seven,
                "Six Post",
                getString(R.string.lorem_ipsum)
            )
        )


        val adapter = FeedAdapter(requireContext(), arraylist)
        rv_feed.layoutManager = LinearLayoutManager(requireContext())

        rv_feed.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )
        rv_feed.adapter = adapter
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_feed, container, false)
    }


}