package com.hmn.ui.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.hmn.ui.MainActivity
import com.hmn.ui.R
import com.hmn.ui.room.MDatabase
import kotlinx.android.synthetic.main.fragment_one.*


class FragmentOne : Fragment() {


    private val ANIMATION_TRANSITION_TIME = 1000L
    private var mHiddenLinearLayoutHeight = 0



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val desc = bundle!!.getString("edit")!!
        edit.text = desc

        val bundle2 = arguments
        val pillTitle = bundle2?.getString("Hello")
        tv_one.text = pillTitle
        val toolbar = view.findViewById<Toolbar>(R.id.back_toolbar)
        toolbar.setNavigationIcon(R.drawable.back_24_red)

        toolbar.setNavigationOnClickListener {
            val activity = activity as MainActivity
            activity.onSupportNavigateUp()
        }


        tb_delete.setOnClickListener {
            val bundlee = arguments
            val a = bundlee!!.getInt("id")
            deleteRoom(a)

            Toast.makeText(requireContext(),"Deleted Successfully",Toast.LENGTH_LONG).show()

            val activity = activity as MainActivity
            activity.onSupportNavigateUp()
        }

        constrain_two.setOnClickListener {
            val activity = activity as MainActivity
            activity.loadFragment(EditTextFragment(),"","",0,"","frgaOne")
        }


        constrain_two.visibility = View.VISIBLE
        constrain_two.getViewTreeObserver().addOnGlobalLayoutListener(
            object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {

                    mHiddenLinearLayoutHeight = constrain_two.getHeight()
                    constrain_two.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this)
                    constrain_two.setVisibility(View.GONE)
                }
            })


        switch_one.setOnCheckedChangeListener { buttonView, isCheck ->
            if (isCheck) {

                if (areElementsVisible()) {
                    hideElements()
                } else {
                    showElements()
                }

            } else {

                constrain_two.visibility = View.GONE

            }

            time_packer.hour = 5
            time_packer.minute = 35


        }

        time_packer.setIs24HourView(true)

        time_packer.setOnTimeChangedListener { p0, hourOfDay, minute ->
            var hour = 0

            if (hourOfDay > 11) {

                hour = hourOfDay

            } else {
                hour = hourOfDay
            }

            tv_time.text = "$hour : $minute"
        }


        time_packer.visibility = View.GONE



        bottom_relative.setOnClickListener {

            if (time_packer.visibility == View.GONE){
                time_packer.visibility = View.VISIBLE
            }else{
                time_packer.visibility = View.GONE
            }

        }



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_one, container, false)

    }

    private fun areElementsVisible(): Boolean {
        return constrain_two.getVisibility() == View.VISIBLE
    }

    private fun hideElements() {
        // Precondition
        if (!areElementsVisible()) {
            Log.w(
                "TAG",
                "The view is already non-visible. Nothing to do here"
            )
            return
        }

        constrain_two
            .animate()
            .setDuration(ANIMATION_TRANSITION_TIME)
            .alpha(0.0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    Log.v(
                        "TAG",
                        "Animation ended. Set the view as gone"
                    )
                    super.onAnimationEnd(animation)
                    constrain_two.setVisibility(View.GONE)
                    constrain_two.animate().setListener(null)

                }
            })


    }

    private fun showElements() {
        // Precondition
        if (areElementsVisible()) {
            Log.w(
                "TAG",
                "The view is already visible. Nothing to do here"
            )
            return
        }

        constrain_two.setVisibility(View.VISIBLE)
        constrain_two.setAlpha(0.0f)
        constrain_two
            .animate()
            .setDuration(ANIMATION_TRANSITION_TIME)
            .alpha(1.0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    //   updateShowElementsButton()
                }
            })

    }

    fun deleteRoom(id:Int){

        MDatabase.getDatabase(requireContext()).categoryDao().deleteById(id)
    }
}