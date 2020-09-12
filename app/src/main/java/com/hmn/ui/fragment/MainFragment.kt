package com.hmn.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmn.ui.MainActivity
import com.hmn.ui.R
import com.hmn.ui.RecyclerViewClickInterface
import com.hmn.ui.adapter.PillAdapter
import com.hmn.ui.room.CategiryEntity
import com.hmn.ui.room.MDatabase
import com.hmn.ui.util.Const
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(), RecyclerViewClickInterface {


    lateinit var myPreference: SharedPreferences

    lateinit var pref: SharedPreferences
    lateinit var ed: SharedPreferences.Editor


    private fun setData(key: String, onOffTextView: TextView, timeTextView: TextView) {
        timeTextView.visibility = View.GONE
        if (myPreference.getBoolean(key, false)) {
            onOffTextView.text = getString(R.string.on_status)
            timeTextView.visibility = View.VISIBLE
            timeTextView.text = myPreference.getString(key + "Time", "")
            val hour = myPreference.getInt(key+"hour",0)
            val min = myPreference.getInt(key+"min",0)
            OnOffReminderFrgment.setReminder(requireActivity().application,hour,min)



        } else {
            onOffTextView.text = getString(R.string.off_status)
            timeTextView.visibility = View.GONE
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myPreference = requireContext().getSharedPreferences(Const.SHARE_PREF, 0)
        pref = requireContext().getSharedPreferences(Const.SHARE_PREF_Two, 0)
        ed = pref.edit()
        setData("first", tv_status_one, tv_time_one)
        setData("second", tv_status_two, tv_time_two)
        setData("third", tv_status_three, tv_time_three)
        setData("fourth", tv_status_four, tv_time_four)
        setData("fivth", tv_status_five, tv_time_five)
        setData("sixth", tv_status_sixth, tv_time_sixth)


        first_const.setOnClickListener {
            val activity = activity as MainActivity
            activity.loadFragment(
                OnOffReminderFrgment(),
                tv_title_one.text.toString(),
                "first",
                1,
                "",
                ""
            )
            ed.putString("title", tv_title_one.text.toString()).apply()
        }


        second_const.setOnClickListener {
            val activity = activity as MainActivity
            activity.loadFragment(
                OnOffReminderFrgment(),
                tv_title_two.text.toString(),
                "second",
                2,
                "",
                ""
            )
            ed.putString("title", tv_title_two.text.toString()).apply()
        }


        third_const.setOnClickListener {
            val activity = activity as MainActivity
            activity.loadFragment(
                OnOffReminderFrgment(),
                tv_title_three.text.toString(),
                "third",
                3,
                "",
                ""
            )
            ed.putString("title", tv_title_three.text.toString()).apply()
        }

        four_const.setOnClickListener {
            val activity = activity as MainActivity
            activity.loadFragment(
                OnOffReminderFrgment(),
                tv_title_four.text.toString(),
                "fourth",
                4,
                "",
                ""
            )
            ed.putString("title", tv_title_four.text.toString()).apply()
        }



        fifth_const.setOnClickListener {
            val activity = activity as MainActivity
            activity.loadFragment(
                OnOffReminderFrgment(),
                tv_title_five.text.toString(),
                "fivth",
                5,
                "",
                ""
            )
            ed.putString("title", tv_title_five.text.toString()).apply()
        }
        sixth_const.setOnClickListener {
            val activity = activity as MainActivity
            activity.loadFragment(
                OnOffReminderFrgment(),
                tv_title_sixth.text.toString(),
                "sixth",
                6,
                "",
                ""
            )
            ed.putString("title", tv_title_sixth.text.toString()).apply()
        }


        tv_add.setOnClickListener {
            val activity = activity as MainActivity
            activity.loadFragment(PillNameAddingFragment(), "", "", 0, "", "")
        }


        val list = MDatabase.getDatabase(requireContext()).categoryDao().getAllCategory()
        for (i in list) {
            i.id
            i.time
        }
        recycler_main_fragment.layoutManager = LinearLayoutManager(requireContext())
        recycler_main_fragment.adapter = PillAdapter(requireContext(), list, this)
        recycler_main_fragment.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)

    }


    override fun onItemClick(position: Int, categiryEntity: CategiryEntity) {
        val activity = activity as MainActivity
        activity.loadFragment(FragmentOne(), categiryEntity.title, "", categiryEntity.id, "", "")
        ed.putString("title", categiryEntity.title).apply()

    }


}