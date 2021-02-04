package com.hilmi.filmgratis.screen.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hilmi.filmgratis.R
import com.hilmi.filmgratis.screen.Home.HomeFragment
import com.hilmi.filmgratis.screen.Theater.TheaterFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var content: FrameLayout? = null
    val fragmentHome = HomeFragment()
    val fragmentTheater = TheaterFragment()
    var activeFragment : Fragment = fragmentHome




    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_Home -> {
                hideShowFragment(fragmentHome)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_Theater -> {
                hideShowFragment(fragmentTheater)
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.selectedItemId=R.id.navigation_Home

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        addHideFragment(fragmentTheater)
        addHideFragment(fragmentHome)



    }


    override fun onBackPressed() {

        if (navigation.selectedItemId==R.id.navigation_Home) {
            super.onBackPressed()
        } else{
            navigation.selectedItemId=R.id.navigation_Home
        }
    }

    private fun hideShowFragment(fragment: Fragment) {
        if (activeFragment != fragment) {
            supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
            activeFragment = fragment
        }

    }

    private fun addHideFragment(fragment : Fragment) {
        if (fragment == activeFragment) {
            supportFragmentManager.beginTransaction().add(R.id.content, fragment).commit()
        }else {
            supportFragmentManager.beginTransaction().add(R.id.content, fragment).hide(fragment).commit()
        }

    }



}
