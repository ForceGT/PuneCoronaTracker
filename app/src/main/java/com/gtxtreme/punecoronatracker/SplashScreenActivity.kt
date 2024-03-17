package com.gtxtreme.punecoronatracker

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gtxtreme.punecoronatracker.databinding.SplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    // Views
    private lateinit var binding: SplashScreenBinding
    private lateinit var logo: ImageView
    private lateinit var pctDescription: TextView
    private lateinit var developedByText: TextView
    private lateinit var devName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        val slideDownAnimation = AnimationUtils
            .loadAnimation(this, R.anim.slide_down)
        val slideInfromBottom = AnimationUtils
            .loadAnimation(this, R.anim.slide_in_from_bottom)
        val slideInfromLeft = AnimationUtils
            .loadAnimation(this, R.anim.slide_in_from_left)
        val slideInfromBottomDelayed =
            AnimationUtils.loadAnimation(this, R.anim.slide_in_from_bottom_delayed)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.splash_screen)
        bindData(binding)

        logo.animation = slideDownAnimation
        developedByText.animation = slideInfromBottom
        devName.animation = slideInfromBottomDelayed
        pctDescription.animation = slideInfromLeft
        Handler().postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 4000
        )
    }

    private fun bindData(binding: SplashScreenBinding) {
        logo = binding.logo
        pctDescription = binding.pctDesc
        devName = binding.devName
        developedByText = binding.developedByText
    }

}