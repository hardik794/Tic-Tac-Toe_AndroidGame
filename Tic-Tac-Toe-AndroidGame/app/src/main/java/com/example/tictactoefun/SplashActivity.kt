package com.example.tictactoefun

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("DEPRECATION")
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        splashimg.alpha = 0f
        splashtext.alpha = 0f

        splashtext.animate()
                .translationY(splashtext.height.toFloat())
                .alpha(1f)
                .setStartDelay(1000).duration = 1200

        splashimg.animate().translationY(splashimg.height.toFloat())
                .alpha(1f).duration = 800


        @Suppress("DEPRECATION")
        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}