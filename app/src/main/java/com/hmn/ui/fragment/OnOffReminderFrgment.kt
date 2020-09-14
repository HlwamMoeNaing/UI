package com.hmn.ui.fragment

import android.app.AlarmManager
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


    lateinit var myPreference: SharedPreferences
    private var format = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myPreference = requireContext().getSharedPreferences(Const.SHARE_PREF, 0)
        val editor = myPreference.edit()


        reminder_bottom_constraint.visibility = View.GONE
        val h = time_picker.hour
        val m = time_picker.minute
        tv_reminder_selected_time.text = StringBuilder().append(h).append(" : ").append(m)
            .append(" ").append(format)

        val toolbar = view.findViewById<Toolbar>(R.id.reminder_toolbar)
        toolbar.setNavigationIcon(R.drawable.back_24_red)

        val bundle = arguments
        val pillTitle = bundle?.getString("Hello")
        val requestCode = bundle?.getInt("id")
        tv_reminder_title.text = pillTitle
        val check = requestCode

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

        time_picker.setIs24HourView(true)

        time_picker.setOnTimeChangedListener(object : TimePicker.OnTimeChangedListener {
            override fun onTimeChanged(p0: TimePicker?, hourOfDay: Int, minute: Int) {

                val format = ""
                val edd = myPreference.edit()
                edd.putInt(bundle?.getString("key")!! + "hour", hourOfDay)
                edd.putInt(bundle.getString("key")!! + "minute", minute)
                edd.apply()



                tv_reminder_selected_time.text =
                    StringBuilder().append(hourOfDay).append(" : ").append(minute)
                        .append(" ").append(format)
            }

        })


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
        reminder_bottom_constraint.setOnClickListener {
            val activity = activity as MainActivity
            activity.loadFragment(EditTextFragment(), "", "", 0, "", "frgReminder")
        }



        toolbar.setNavigationOnClickListener {
            val alarmManager =
                requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(requireContext(), AlarmReceiver::class.java)
            intent.putExtra("pilltitle", tv_reminder_title.text.toString())
            val pendingIntent = PendingIntent.getBroadcast(
                requireContext(),
                requestCode!!,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            val hour = time_picker.currentHour
            val minute = time_picker.currentMinute
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, 0)

            if (reminder_switch.isChecked){
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            }else{
                alarmManager.cancel(pendingIntent)
            }

            val activity = activity as MainActivity
            val edd = myPreference.edit()
            edd.putString(
                bundle.getString("key")!! + "Time",
                tv_reminder_selected_time.text.toString()
            )
            edd.apply()

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


}


