package com.example.tictactoefun

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_choose.*
import java.util.*

@Suppress("DEPRECATION")
class ChooseActivity : AppCompatActivity() {

    var player1: CharSequence = "Player 1"
    var player2: CharSequence = "Player 2"
    var selectedsingleplayer = false
    var player1ax = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_choose)

        val players =
            intent.getCharSequenceArrayExtra("playersnames")
        player1 = players!![0]
        player2 = players!![1]

//        val textView = findViewById<TextView>(R.id.text3)

        selectedsingleplayer = intent.getBooleanExtra("selectedsingleplayer", true)
        if (!selectedsingleplayer) {
            text3.text = "$player1 pick your side"
        }

//        val imageView =
//            findViewById<ImageView>(R.id.imageView)
//        val imageView2 =
//            findViewById<ImageView>(R.id.imageView2)
        imageView.setColorFilter(applicationContext.resources.getColor(R.color.white))
        imageView2.setColorFilter(applicationContext.resources.getColor(R.color.white))
//        val r1 = findViewById<RadioButton>(R.id.player1o)
//        val r2 = findViewById<RadioButton>(R.id.player1x)
        val textColor = Color.parseColor("#e5e9ea")
        val textColorBlue = Color.parseColor("#3b7df8")

        player1o.post(object : Runnable {
            override fun run() {
                if (player1o.isChecked) {
                    player1o.buttonTintList = ColorStateList.valueOf(textColorBlue)
                } else {
                    player1o.buttonTintList = ColorStateList.valueOf(textColor)
                }
                player1o.postDelayed(this, 10)
            }
        })

        player1x.post(object : Runnable {
            override fun run() {
                if (player1x.isChecked) {
                    player1x.buttonTintList = ColorStateList.valueOf(textColorBlue)
                } else {
                    player1x.buttonTintList = ColorStateList.valueOf(textColor)
                }
                player1x.postDelayed(this, 10)
            }
        })

        imageView2.setOnClickListener {
            player1x.isChecked = false
            player1o.isChecked = true
            imageView2.setColorFilter(
                applicationContext.resources.getColor(R.color.transparent)
            )
            player1ax = false
            imageView2.setImageResource(R.drawable.oo)
            imageView.setImageResource(R.drawable.xxsh)
            imageView.setColorFilter(
                applicationContext.resources.getColor(R.color.tint2)
            )
        }

        imageView.setOnClickListener {
            player1o.isChecked = false
            player1x.isChecked = true
            player1ax = true
            imageView.setColorFilter(
                applicationContext.resources.getColor(R.color.transparent)
            )
            imageView2.setImageResource(R.drawable.ooh)
            imageView.setImageResource(R.drawable.xxs)
            imageView2.setColorFilter(
                applicationContext.resources.getColor(R.color.tint2)
            )
        }

        player1o.setOnClickListener(View.OnClickListener {
            player1x.isChecked = false
            player1ax = false
            imageView2.setImageResource(R.drawable.oo)
            imageView.setImageResource(R.drawable.xxsh)
            imageView2.setColorFilter(
                applicationContext.resources.getColor(R.color.transparent)
            )
            imageView.setColorFilter(
                applicationContext.resources.getColor(R.color.tint2)
            )
        })

        player1x.setOnClickListener(View.OnClickListener {
            player1o.isChecked = false
            player1ax = true
            imageView2.setImageResource(R.drawable.ooh)
            imageView.setImageResource(R.drawable.xxs)
            imageView.setColorFilter(
                applicationContext.resources.getColor(R.color.transparent)
            )
            imageView2.setColorFilter(
                applicationContext.resources.getColor(R.color.tint2)
            )
        })

        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (player1o.isChecked || player1x.isChecked) {
                    val ds = findViewById<Button>(R.id.button)
                    ds.setOnClickListener {
                        val players1 =
                            intent.getCharSequenceArrayExtra("playersnames")
                        player1 = players1!![0]
                        player2 = players1[1]
                        val i = Intent(this@ChooseActivity, SceneActivity::class.java)
                        val players =
                            arrayOf(player1, player2)
                        i.putExtra("playersnames", players)
                        i.putExtra("player1ax", player1ax)
                        i.putExtra("selectedsingleplayer", selectedsingleplayer)
                        startActivity(i)
                    }
                }
            }
        }, 0, 20) //put here time 1000 milliseconds = 1 second


    }

//    override fun onBackPressed() {
//        val intent = Intent(this@ChooseActivity, NameActivity::class.java)
//        startActivity(intent)
//    }
}