package com.example.tracking_corona

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val iconCorona =ObjectAnimator.ofFloat(imgCorona, View.ALPHA, 0f,1f)
        iconCorona.duration = 1500;
        iconCorona.repeatMode = ObjectAnimator.REVERSE
        iconCorona.start()
        Handler().postDelayed({

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1300)

    }
}