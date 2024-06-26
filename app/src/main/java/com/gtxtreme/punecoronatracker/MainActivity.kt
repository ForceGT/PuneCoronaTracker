package com.gtxtreme.punecoronatracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.gtxtreme.punecoronatracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "PuneCoronaTracker"

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (savedInstanceState == null) {
            binding.navbar.setItemSelected(R.id.home, true)
            supportFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.fragment_enter,
                R.anim.fragment_exit
            ).replace(R.id.container_frame, HomeFragment()).commit()
        }


        binding.navbar.setOnItemSelectedListener { id ->
            var fragment: Fragment? = null
            when (id) {
                R.id.home -> fragment = HomeFragment()
                R.id.graphical_stats -> fragment = GraphicalStatsFragment()
                R.id.sources -> fragment = SourcesFragment()
                R.id.wardDetail -> fragment = WardDetailFragment()
            }
            if (fragment != null) {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit)
                    .replace(R.id.container_frame, fragment)
                    .commit()
            } else {
                Log.e(TAG, "Error Creating Fragment")
            }
        }
    }
}
