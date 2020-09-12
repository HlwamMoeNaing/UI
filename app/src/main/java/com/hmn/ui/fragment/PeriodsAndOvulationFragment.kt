package com.hmn.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.hmn.ui.MainActivity
import com.hmn.ui.R
import kotlinx.android.synthetic.main.fragment_priods_and_ovulation.*
import kotlinx.android.synthetic.main.picker.*

class PeriodsAndOvulationFragment : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_done.setOnClickListener {
            val activity = activity as MainActivity
            activity.onSupportNavigateUp()
        }

        period_number_picker.minValue = 0
        period_number_picker.maxValue = 11

        cycle_number_picker.minValue = 20
        cycle_number_picker.maxValue = 60


        number_picker.minValue = 11
        number_picker.maxValue = 20

        period_number_picker.visibility = View.GONE
        constraint_one.setOnClickListener {
            if (period_number_picker.visibility == View.GONE){
                period_number_picker.visibility = View.VISIBLE
                period_number_picker.setOnValueChangedListener(object : NumberPicker.OnValueChangeListener {
                    override fun onValueChange(p0: NumberPicker?, oldValue: Int, newValue: Int) {
                        tv_period_days.text = "$newValue Days"
                    }
                })
            }else{
                period_number_picker.visibility = View.GONE
            }
        }


        cycle_number_picker.visibility = View.GONE
        cons.setOnClickListener {
            if (cycle_number_picker.visibility == View.GONE){
                cycle_number_picker.visibility = View.VISIBLE
                cycle_number_picker.setOnValueChangedListener(object : NumberPicker.OnValueChangeListener {
                    override fun onValueChange(p0: NumberPicker?, oldValue: Int, newValue: Int) {
                        tv_cycle_days.text = "$newValue Days"
                    }
                })
            }else{
                cycle_number_picker.visibility = View.GONE
            }
        }


        cycle_number_picker.visibility = View.GONE
        cons.setOnClickListener {
            if (cycle_number_picker.visibility == View.GONE){
                cycle_number_picker.visibility = View.VISIBLE
                cycle_number_picker.setOnValueChangedListener(object : NumberPicker.OnValueChangeListener {
                    override fun onValueChange(p0: NumberPicker?, oldValue: Int, newValue: Int) {
                        tv_cycle_days.text = "$newValue Days"
                    }
                })
            }else{
                cycle_number_picker.visibility = View.GONE
            }
        }



        number_picker.visibility = View.GONE
        include.setOnClickListener {
            if (number_picker.visibility == View.GONE){
                number_picker.visibility = View.VISIBLE
                number_picker.setOnValueChangedListener(object : NumberPicker.OnValueChangeListener {
                    override fun onValueChange(p0: NumberPicker?, oldValue: Int, newValue: Int) {
                        tvSubmit.text = "$newValue Days"
                    }
                })
            }else{
                number_picker.visibility = View.GONE
            }


        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_priods_and_ovulation, container, false)

        return view
    }
}