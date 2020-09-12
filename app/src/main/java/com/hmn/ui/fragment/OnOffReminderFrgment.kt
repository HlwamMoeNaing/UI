package com.hmn.ui.fragment

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.hmn.ui.MainActivity
import com.hmn.ui.R
import com.hmn.ui.clas.AlarmReceiver
import com.hmn.ui.util.Const
import kotlinx.android.synthetic.main.fragment_on_off_reminder_frgment.*
import java.util.*


class OnOffReminderFrgment : Fragment() {

    private var time: String = ""
    lateinit var myPreference: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myPreference = requireContext().getSharedPreferences(Const.SHARE_PREF, 0)
        val editor = myPreference.edit()
        reminder_bottom_constraint.visibility = View.GONE
        val h = time_picker.hour
        val m = time_picker.minute
        tv_reminder_selected_time.text = "$h : $m"

        val toolbar = view.findViewById<Toolbar>(R.id.reminder_toolbar)
        toolbar.setNavigationIcon(R.drawable.back_24_red)

        val bundle = arguments
        val pillTitle = bundle?.getString("Hello")
        tv_reminder_title.text = pillTitle


        val bundle2 = arguments
        val desc = bundle2?.getString("edit")
        tv_reminder_desc.text = desc










        time_picker.visibility = View.GONE
        relative_two.setOnClickListener {
            if (time_picker.visibility == View.GONE) {
                time_picker.visibility = View.VISIBLE
            } else {
                time_picker.visibility = View.GONE
            }
        }

        reminder_switch.setOnCheckedChangeListener { buttonView, isCkeck ->


            if (isCkeck) {

                reminder_bottom_constraint.visibility = View.VISIBLE
                editor.putBoolean(bundle!!.getString("key")!!, true).apply()


            } else {
                reminder_bottom_constraint.visibility = View.GONE
                editor.putBoolean(bundle!!.getString("key")!!, false).apply()

            }
        }
        reminder_switch.isChecked = myPreference.getBoolean(bundle!!.getString("key")!!, false)


        time_picker.setIs24HourView(true)
        time_picker.setOnTimeChangedListener(object : TimePicker.OnTimeChangedListener {
            override fun onTimeChanged(p0: TimePicker?, hourOfDay: Int, minute: Int) {
                var hour = 0
                if (hourOfDay > 11) {
                    hour = hourOfDay
                } else {
                    hour = hourOfDay
                }

                time = "$hour : $minute"
                tv_reminder_selected_time.text = "$hour : $minute"


                var timeValue = ""

                if (reminder_switch.isChecked == false) {
                    timeValue = "sdafsda"

                } else {
                    val timePicker = TimePicker(requireContext())
                    timeValue = tv_reminder_selected_time.text.toString()
                }

                val edd = myPreference.edit()
                edd.putString(bundle.getString("key")!! + "Time", timeValue)
                edd.putInt(bundle.getString("key")!! + "hour", hourOfDay)
                edd.putInt(bundle.getString("key")!! + "min", minute)
                edd.apply()
            }

        })



        reminder_bottom_constraint.setOnClickListener {
            val activity = activity as MainActivity
            activity.loadFragment(EditTextFragment(), "", "", 0, "", "frgReminder")
        }



        toolbar.setNavigationOnClickListener {
            val activity = activity as MainActivity
            activity.onSupportNavigateUp()

        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_on_off_reminder_frgment, container, false)


        return view
    }


    companion object {

        fun setReminder(application: Application, hour: Int, min: Int) {
            val alarmManager = application.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(application, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(application, 0, intent, 0)

            val calender = Calendar.getInstance()
            calender.set(
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH),
                hour,
                min,
                0
            )
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calender.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )

        }
    }


}