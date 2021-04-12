package com.example.tictactoefun

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_name.*
import java.util.*
@Suppress("DEPRECATION")
class NameActivity : AppCompatActivity() {

    var player1: CharSequence = "1"
    var player2: CharSequence = "2"
    private var leng = 0
    var selectedsingleplayer = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_name)

        playerone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                player1 = s.toString()
            }

            override fun afterTextChanged(s: Editable) {}
        })


//        Timer().scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//                leng = playerone.text?.length ?:leng
//            }
//        }, 0, 2)
    //put here time 1000 milliseconds = 1 second


//        Timer().scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//                if (leng > 1) {

                    continue_btn.setOnClickListener {
                        val i = Intent(this@NameActivity, ChooseActivity::class.java)
                        val players =
                            arrayOf<CharSequence>(player1, player2)
                        i.putExtra("playersnames", players)
                        i.putExtra("selectedsingleplayer", selectedsingleplayer)
                        startActivity(i)
                    }
//                }
//            }
//        }, 0, 20)
//put here time 1000 milliseconds = 1 second

    }

    override fun onBackPressed() {
        val intent = Intent(this@NameActivity, MainActivity::class.java)
        startActivity(intent)
    }
}