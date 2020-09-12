package com.hmn.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.hmn.ui.MainActivity
import com.hmn.ui.R
import com.hmn.ui.util.Const
import kotlinx.android.synthetic.main.fragment_edit_text.*
import kotlinx.android.synthetic.main.fragment_one.*


class EditTextFragment : Fragment() {

    lateinit var pref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val toolbar = view.findViewById<Toolbar>(R.id.back_toolbar)
        toolbar.setNavigationIcon(R.drawable.back_24_red)
        pref = requireContext().getSharedPreferences(Const.SHARE_PREF_Two, 0)
        val ac =  pref.getString("title", "")!!

        toolbar.setNavigationOnClickListener {

            val activity = activity as MainActivity

            val bundle = arguments
            val tag = bundle?.getString("tag")!!
            if (tag.contains("fragAdd")){
                activity.loadFragment(PillNameAddingFragment(),ac,"",0,edit_text.text.toString(),"")

            }else if (tag.contains("frgaOne")) {


                activity.loadFragment(
                    FragmentOne(),
                    ac,
                    "",
                    0,
                    edit_text.text.toString(),
                    ""
                )
            }else if (tag.contains("frgReminder")){
                activity.loadFragment(
                    OnOffReminderFrgment(),
                    ac,
                    "",
                    0,
                    edit_text.text.toString(),
                    ""
                )
            }

        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_edit_text, container, false)
    }

}