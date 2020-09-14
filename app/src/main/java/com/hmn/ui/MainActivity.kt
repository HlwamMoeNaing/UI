package com.hmn.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.hmn.ui.fragment.MainFragment
import com.hmn.ui.fragment.NewFeedFragment
import com.hmn.ui.fragment.PeriodsAndOvulationFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var container: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // NotificationUtils.createNotification(applicationContext,"Lee","Fuck")

        container = findViewById(R.id.main_container)
        val navigationView = findViewById<NavigationView>(R.id.nav_view_main)
        navigationView.setNavigationItemSelectedListener(this)


        supportFragmentManager.beginTransaction().replace(R.id.main_container, MainFragment())
            .commit()

    }





    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {


            R.id.menu_one -> {
                loadFragment(NewFeedFragment(), "","", 0,"","")

            }

            R.id.menu_two -> loadFragment(PeriodsAndOvulationFragment(), "","", 0,"","")

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return false
    }

    fun loadFragment(fragment: Fragment, str: String,key:String, id: Int,edit:String,tag:String) {
        val bundle = Bundle()
        bundle.putString("Hello", str)
        bundle.putString("key", key)
        bundle.putString("edit", edit)
        bundle.putString("tag", tag)
        bundle.putInt("id", id)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment)
            .addToBackStack(null).commit()

    }

    fun reloadMainFragment(fragment: Fragment, value: String){
        val bundle = Bundle()
        bundle.putString("key", value)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment)
            .addToBackStack(null).commit()

    }
    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment())
            .commit()
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }




}