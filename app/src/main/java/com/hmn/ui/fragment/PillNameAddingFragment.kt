package com.hmn.ui.fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.hmn.ui.MainActivity
import com.hmn.ui.R
import com.hmn.ui.clas.AlarmReceiver
import com.hmn.ui.room.CategiryEntity
import com.hmn.ui.room.MDatabase
import kotlinx.android.synthetic.main.fragment_pill_name_adding.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.time.ExperimentalTime


class PillNameAddingFragment : Fragment() {




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle2 = arguments
        val desc = bundle2?.getString("edit")
        tv_pill_desc.text = desc

        val toolbar = view.findViewById<Toolbar>(R.id.back_toolbar)

        toolbar.setNavigationIcon(R.drawable.back_24_red)

        toolbar.setNavigationOnClickListener {
            val activity = activity as MainActivity
            activity.onSupportNavigateUp()
        }
        pill_time_packer.visibility = View.GONE




        pill_relative_layout.setOnClickListener {
            if (pill_time_packer.visibility == View.GONE){
                pill_time_packer.visibility = View.VISIBLE
            }else{
                pill_time_packer.visibility = View.GONE
            }
        }



        pill_time_packer.setIs24HourView(true)
        pill_time_packer.setOnTimeChangedListener(object : TimePicker.OnTimeChangedListener {
            override fun onTimeChanged(p0: TimePicker?, hour: Int, minute: Int) {
                tv_time.text = "$hour : $minute"


            }


        })

        rl_upper.setOnClickListener {
            val activit = activity as MainActivity

            activit.loadFragment(
                EditTextFragment(),
                tv_pill_desc.text.toString(),
                "",
                0,
                "",
                "fragAdd"
            )
        }


        tb_done.setOnClickListener {

            if (edit_text_pill.text.toString().isNullOrBlank()  && tv_time.text.toString().isNullOrBlank() ){
                Toast.makeText(requireContext(),"Please Fill Requird Fied",Toast.LENGTH_LONG).show()
            }else{
                val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val intent = Intent(requireContext(), AlarmReceiver::class.java)
                intent.putExtra("title", edit_text_pill.text)

                //  intent.putExtra("todo", tvedit.text)
                val pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, 0)

                val calender = Calendar.getInstance()
                calender.set(
                    calender.get(Calendar.YEAR),
                    calender.get(Calendar.MONTH),
                    calender.get(Calendar.DAY_OF_MONTH),
                    pill_time_packer.hour,
                    pill_time_packer.minute,
                    0
                )
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calender.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )

                addArray()

                val activity = activity as MainActivity
                activity.onSupportNavigateUp()

            }





        }

    }




    @ExperimentalTime
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pill_name_adding, container, false)

    }
    fun addArray(){
        val arrayList = ArrayList<CategiryEntity>()
        val pillTitle = edit_text_pill.text.toString()
        if (TextUtils.isEmpty(pillTitle)) {
            edit_text_pill.error = "Fill Pill Name"
            return
        }

        val time = tv_time.text.toString()

        if (TextUtils.isEmpty(time)) {
            tv_time.error = "Choose Time"
            return
        }

        arrayList.add(CategiryEntity(0, pillTitle, time, false))

        MDatabase.getDatabase(requireContext()).categoryDao().insertCategory(arrayList)
    }




}