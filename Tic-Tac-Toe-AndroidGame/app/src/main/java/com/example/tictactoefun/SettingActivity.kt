package com.example.tictactoefun

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_setting.*
import java.util.*

@Suppress("DEPRECATION")
class SettingActivity : AppCompatActivity() {

//    val EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X"
//    val EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y"
//    var rootLayout: View? = null
//    private var revealX = 0
    private val PREFS_NAME = "vibration"
    private val PREF_VIBRATION = "TicVib"
//    private var revealY = 0
    private var Vibration = false
    private var isChecked = false
    private lateinit var Randomfirst: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        Vibration = preferences.getBoolean(PREF_VIBRATION, true)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_setting)
        val intent = intent


        swith_vib.isChecked = Vibration
        swith_vib.setOnClickListener {
            if (Vibration) {
                isChecked = false
                val editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit()
                editor.putBoolean(PREF_VIBRATION, isChecked)
                editor.apply()
            } else {
                isChecked = true
                val editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit()
                editor.putBoolean(PREF_VIBRATION, isChecked)
                editor.apply()
            }
        }


        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val savedValue = sharedPreferences.getInt("key", 0)


//        val spinner2 = findViewById<View>(R.id.spinner) as Spinner

        if (savedValue == 1) Randomfirst =
            arrayOf("Easy", "Random", "Medium", "Hard", "Impossible")
        if (savedValue == 2) Randomfirst =
            arrayOf("Medium", "Random", "Easy", "Hard", "Impossible")
        if (savedValue == 3) Randomfirst =
            arrayOf("Hard", "Random", "Easy", "Medium", "Impossible")
        if (savedValue == 4) Randomfirst =
            arrayOf("Impossible", "Random", "Easy", "Medium", "Hard")
        if (savedValue == 5) Randomfirst =
            arrayOf("Random", "Easy", "Medium", "Hard", "Impossible")
        if (savedValue == 0) Randomfirst =
            arrayOf("Random", "Easy", "Medium", "Hard", "Impossible")

        val dataAdapter = ArrayAdapter(
            this,
            R.layout.spinner_item, Randomfirst
        )


        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinner.adapter = dataAdapter


        var statusCheck: List<String?> = ArrayList()
        statusCheck = listOf("Easy")
        spinner.setSelection(statusCheck.indexOf(1.toString()))





        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                val sharedPreferences =
                    PreferenceManager.getDefaultSharedPreferences(applicationContext)
                val editor = sharedPreferences.edit()
                if (selectedItem == "Random") {
                    editor.putInt("key", 5)
                }
                if (selectedItem == "Easy") {
                    editor.putInt("key", 1)
                }
                if (selectedItem == "Medium") {
                    editor.putInt("key", 2)
                }
                if (selectedItem == "Hard") {
                    editor.putInt("key", 3)
                }
                if (selectedItem == "Impossible") {
                    editor.putInt("key", 4)
                }
                editor.commit()
            }

            // to close the onItemSelected
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        r6.setOnClickListener { rating() }

        r5.setOnClickListener {
            Toast.makeText(applicationContext, "App doesn't have ads", Toast.LENGTH_SHORT)
                .show()
        }

        r7.setOnClickListener { feedbacks() }



//        rootLayout = findViewById(R.id.rootlay)
//
//        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
//            intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X)
//            &&
//            intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)
//        ) {
//            rootLayout.setVisibility(View.INVISIBLE)
//
//            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0)
//            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0)
//
//            val viewTreeObserver = rootLayout.getViewTreeObserver()
//            if (viewTreeObserver.isAlive) {
//                viewTreeObserver.addOnGlobalLayoutListener(
//                    object : OnGlobalLayoutListener {
//                        override fun onGlobalLayout() {
//                            revealActivity(
//                                revealX, revealY
//                            )
//                            rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this)
//                        }
//                    })
//            }
//        } else {
//            rootLayout.setVisibility(View.VISIBLE)
//        }
    }

//    protected fun revealActivity(x: Int, y: Int) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val finalRadius = (Math.max(
//                rootLayout!!.width,
//                rootLayout!!.height
//            ) * 1.1).toFloat()
//
//// create the animator for this view (the start radius is zero)
//            val circularReveal =
//                ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0f, finalRadius)
//            circularReveal.duration = 400
//            circularReveal.interpolator = AccelerateInterpolator()
//
//// make the view visible and start the animation
//            rootLayout!!.visibility = View.VISIBLE
//            circularReveal.start()
//        } else {
//            finish()
//        }
//    }

//    protected fun unRevealActivity() {
//        val finalRadius = (Math.max(
//            rootLayout!!.width,
//            rootLayout!!.height
//        ) * 1.1).toFloat()
//        val circularReveal = ViewAnimationUtils.createCircularReveal(
//            rootLayout, revealX, revealY, finalRadius, 0f
//        )
//        circularReveal.duration = 400
//        circularReveal.addListener(
//            object : AnimatorListenerAdapter() {
//                override fun onAnimationEnd(animation: Animator) {
//                    rootLayout!!.visibility = View.INVISIBLE
//
//                    //finish Activity.
//                    finish()
//                }
//            })
//        circularReveal.start()
//    }

    private fun rating() {
        val uri =
            Uri.parse("market://details?id=" + applicationContext.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + applicationContext.packageName)
                )
            )
        }
    }

    private fun feedbacks() {
        val intent = Intent(Intent.ACTION_SEND)
        val recipients = arrayOf("strba.dev@gmail.com")
        intent.putExtra(Intent.EXTRA_EMAIL, recipients)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Tic Tac Toe Feedbacks")
        intent.putExtra(Intent.EXTRA_CC, "strba.dev@gmail.com")
        intent.type = "text/html"
        intent.setPackage("com.google.android.gm")
        startActivity(Intent.createChooser(intent, "Send mail"))
    }

//    override fun onBackPressed() {
////        unRevealActivity()
////        val intent = Intent(this@SettingActivity, MainActivity::class.java)
////        startActivity(intent)
//
//    }
}
