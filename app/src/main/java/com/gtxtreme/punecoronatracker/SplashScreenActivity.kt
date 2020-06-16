package com.gtxtreme.punecoronatracker

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val slideDownAnimation = AnimationUtils.loadAnimation(this,R.anim.slide_down)
        val slideInfromBottom = AnimationUtils.loadAnimation(this,R.anim.slide_in_from_bottom)
        val slideInfromLeft = AnimationUtils.loadAnimation(this,R.anim.slide_in_from_left)
        val slideInfromBottomDelayed = AnimationUtils.loadAnimation(this,R.anim.slide_in_from_bottom_delayed)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        logo.animation = slideDownAnimation
        developedByText.animation = slideInfromBottom
        devName.animation = slideInfromBottomDelayed
       pct_desc.animation = slideInfromLeft
        Handler().postDelayed(
            {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            },4000
        )
    }
}