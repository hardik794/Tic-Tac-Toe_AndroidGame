package com.example.tictactoefun

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import code.iamstcom.tictactoe.SoftKeyboardStateHelper
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_two_name.*
@Suppress("DEPRECATION")
class TwoNameActivity : AppCompatActivity() {

    var player1: CharSequence = "Player 1"
    var player2: CharSequence = "Player 2"
    var selectedsingleplayer = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_two_name)

//        val softKeyboardStateListener: SoftKeyboardStateHelper.SoftKeyboardStateListener =
//            object : SoftKeyboardStateHelper.SoftKeyboardStateListener {
//                override fun onSoftKeyboardOpened(keyboardHeightInPx: Int) {
//                    imageView4.visibility = View.GONE
//                    continue_btn2.visibility = View.GONE
//                }
//
//                override fun onSoftKeyboardClosed() {
//                    imageView4.visibility = View.VISIBLE
//                    continue_btn2.visibility = View.VISIBLE
//                }
//            }
//
//        val softKeyboardStateHelper =
//            SoftKeyboardStateHelper(applicationContext, findViewById(R.id.parent))
//        softKeyboardStateHelper.addSoftKeyboardStateListener(softKeyboardStateListener)
//
//
//        val imm = applicationContext
//            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        player_one.addTextChangedListener(object : TextWatcher {
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

        player_two.addTextChangedListener(object : TextWatcher {
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
                player2 = s.toString()
            }

            override fun afterTextChanged(s: Editable) {}
        })


        continue_btn2.setOnClickListener {
            val i = Intent(this@TwoNameActivity, ChooseActivity::class.java)
            i.putExtra("selectedsingleplayer", selectedsingleplayer)
            val players =
                arrayOf(player1, player2)
            i.putExtra("playersnames", players)
            startActivity(i)
        }

    }

    override fun onBackPressed() {
        val intent = Intent(this@TwoNameActivity, MainActivity::class.java)
        startActivity(intent)
    }

}