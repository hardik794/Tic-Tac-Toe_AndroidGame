package com.example.tictactoefun

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        ai_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, NameActivity::class.java)
            startActivity(intent)
        }

        friend_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, TwoNameActivity::class.java)
            startActivity(intent)
        }

        setting_btn.setOnClickListener(View.OnClickListener { v ->
            val myRotation = AnimationUtils.loadAnimation(
                applicationContext,
                R.anim.rotate
            )
            myRotation.repeatCount = 0
            setting_btn.startAnimation(myRotation)
            @Suppress("DEPRECATION")
            handler = Handler()
            handler.postDelayed({ presentActivity(v) }, 900)
        })
    }

    private fun presentActivity(view: View) {
        val options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "transition")
        val revealX = (view.x + view.width / 2).toInt()
        val revealY = (view.y + view.height / 2).toInt()
        val intent = Intent(this, SettingActivity::class.java)
//        intent.putExtra(SettingActivity.EXTRA_CIRCULAR_REVEAL_X, revealX)
//        intent.putExtra(SettingActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY)
        ActivityCompat.startActivity(this, intent, options.toBundle())
    }

    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
}