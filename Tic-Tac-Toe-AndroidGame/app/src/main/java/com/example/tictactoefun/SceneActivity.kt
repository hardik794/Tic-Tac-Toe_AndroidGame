package com.example.tictactoefun

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Vibrator
import android.preference.PreferenceManager
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import kotlinx.android.synthetic.main.activity_scene.*
import java.util.*

@Suppress("DEPRECATION")
class SceneActivity : AppCompatActivity() {

    var easy = true
    var medium = false
    var hard = false
    var impossible = false
    var wins = false
    var returns = false
    var player1ax = false
    var r = Random()
    var flag = 0
    var ax:Int = 10
    var zero:Int = 1
    var sensorflag:Int = 0
    var win:Int = 0
    var i:Int = 0
    var game:Int = 1
    var prevrow:Int = 0
    var prevcol:Int = 0
    var summ = 0
    var ctrflag:Int = 0
    var night:Int = 0
    var resetchecker:Int = 1
    var currentgamedonechecker:Int = 0
    var score1 = 0
    var score2:Int = 0
    var drawchecker:Int = 0
    var tracker = Array(3) { IntArray(3) }
    var sum = IntArray(8)
    var buttonpressed = Array(3) { IntArray(3) }
    var selectedsingleplayer = false
    private var savedValue = 0
    var player1: CharSequence = "Player 1"
    var player2: CharSequence = "Player 2"
    private var Vibration = false
    private val PREFS_NAME = "vibration"
    private val PREF_VIBRATION = "TicVib"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_scene)

        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val preferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE)
                Vibration = preferences.getBoolean(PREF_VIBRATION, true)
                val sharedPreferences =
                    PreferenceManager.getDefaultSharedPreferences(applicationContext)
                savedValue = sharedPreferences.getInt("key", 0)
            }
        }, 0, 2) //put here time 1000 milliseconds = 1 second

//        val imageButton = findViewById<ImageButton>(R.id.imageButton2)
        imageButton2.setOnClickListener { v ->
            val myRotation = AnimationUtils.loadAnimation(
                applicationContext,
                R.anim.rotate
            )
            myRotation.repeatCount = 0
            imageButton2.startAnimation(myRotation)
            Handler().postDelayed({ presentActivity(v) }, 900)
        }


        val players =
            intent.getCharSequenceArrayExtra("playersnames")

        player1 = players!![0]
        player2 = players!![1]

        player1ax = intent.getBooleanExtra("player1ax", true)
        selectedsingleplayer = intent.getBooleanExtra("selectedsingleplayer", true)

        if (player1ax) {
            ax = 1
            zero = 10
        }
//        val textName = findViewById<TextView>(R.id.NameText)
//        val textName2 = findViewById<TextView>(R.id.NameText2)

        NameText.text = player1

        if (!selectedsingleplayer) {
            NameText2.text = player2
            Toast.makeText(this, "$player1\'s turn", Toast.LENGTH_SHORT).show()
        }
    }

    fun kzz(view: View?) {
        if (win == 0 && buttonpressed[0][0] == 0) {
            if (flag % 2 == 0)
                tracker[0][0] = ax
            else
                tracker[0][0] = zero
            printBoard()
            winchecker()
            cpuplay()
            flag++
            buttonpressed[0][0]++
        }
    }

    fun kzo(view: View?) {
        if (win == 0 && buttonpressed[0][1] == 0) {
            if (flag % 2 == 0)
                tracker[0][1] = ax
            else
                tracker[0][1] = zero
            printBoard()
            winchecker()
            cpuplay()
            buttonpressed[0][1]++
            flag++
        }
    }

    fun kzt(view: View?) {
        if (win == 0 && buttonpressed[0][2] == 0) {
            if (flag % 2 == 0)
                tracker[0][2] = ax
            else
                tracker[0][2]= zero
            printBoard()
            winchecker()
            cpuplay()
            buttonpressed[0][2]++
            flag++
        }
    }

    fun koz(v: View?) {
        if (win == 0 && buttonpressed[1][0] == 0) {
            if (flag % 2 == 0)
                tracker[1][0] = ax
            else
                tracker[1][0] = zero
            printBoard()
            winchecker()
            cpuplay()
            ++buttonpressed[1][0]
            flag++
        }
    }

    fun koo(v: View?) {
        if (win == 0 && buttonpressed[1][1] == 0) {
            if (flag % 2 == 0)
                tracker[1][1] = ax
            else
                tracker[1][1] = zero
            printBoard()
            winchecker()
            cpuplay()
            ++buttonpressed[1][1]
            flag++
        }
    }

    fun kot(v: View?) {
        if (win == 0 && buttonpressed[1][2] == 0) {
            if (flag % 2 == 0)
                tracker[1][2] = ax
            else
                tracker[1][2] = zero
            printBoard()
            winchecker()
            cpuplay()
            ++buttonpressed[1][2]
            flag++
        }
    }

    fun ktz(v: View?) {
        if (win == 0 && buttonpressed[2][0] == 0) {
            if (flag % 2 == 0)
                tracker[2][0] = ax
            else
                tracker[2][0] = zero
            printBoard()
            winchecker()
            cpuplay()
            ++buttonpressed[2][0]
            flag++
        }
    }

    fun kto(v: View?) {
        if (win == 0 && buttonpressed[2][1] == 0) {
            if (flag % 2 == 0)
                tracker[2][1] = ax
            else
                tracker[2][1] = zero
            printBoard()
            winchecker()
            cpuplay()
            ++buttonpressed[2][1]
            flag++
        }
    }

    fun ktt(v: View?) {
        if (win == 0 && buttonpressed[2][2] == 0) {
            if (flag % 2 == 0)
                tracker[2][2] = ax
            else
                tracker[2][2] = zero
            printBoard()
            winchecker()
            cpuplay()
            ++buttonpressed[2][2]
            flag++
        }
    }

    fun cpuplay() {
        if (selectedsingleplayer && win == 0) {
            val handler = Handler()
            val t = Timer()
            t.schedule(object : TimerTask() {
                override fun run() {
                    handler.post { //add code to be executed after a pause
                        if (ifcpuwin())
                        else if (ifopowin())
                        else if (emptycentre())
                        else if (emptycorner())
                        else emptyany()
                        printBoard()
                        winchecker()
                        flag++
                    }
                }
            }, 110)
            return
        }
    }

    fun ifcpuwin(): Boolean {
        if (!easy) {
            i = 0
            while (i < 8) {
                if (sum[i] == 2 * zero) {
                    if (i == 0) {
                        for (x in 0..2)
                            if (tracker[0][x] == 0)
                                tracker[0][x]= zero
                    }
                    if (i == 1) {
                        for (x in 0..2)
                            if (tracker[1][x] == 0)
                                tracker[1][x] = zero
                    }
                    if (i == 2) {
                        for (x in 0..2)
                            if (tracker[2][x] == 0)
                                tracker[2][x] = zero
                    }
                    if (i == 3) {
                        for (x in 0..2)
                            if (tracker[x][0] == 0)
                                tracker[x][0] = zero
                    }
                    if (i == 4) {
                        for (x in 0..2)
                            if (tracker[x][1] == 0)
                                tracker[x][1] = zero
                    }
                    if (i == 5) {
                        for (x in 0..2)
                            if (tracker[x][2] == 0)
                                tracker[x][2] = zero
                    }
                    if (i == 6) {
                        for (y in 0..2)
                            for (x in 0..2)
                                if (x == y)
                                    if (tracker[x][y] == 0)
                                        tracker[x][y] = zero
                    }
                    if (i == 7) {
                        if (tracker[0][2] == 0)
                            tracker[0][2] = zero
                        else if (tracker[1][1] == 0)
                            tracker[1][1] = zero
                        else
                            tracker[2][0] = zero
                    }
                    return true
                }
                i++
            }
        }
        return false
    }

    fun ifopowin(): Boolean {
        if (!easy || !medium) {
            i = 0
            while (i < 8) {
                if (sum[i] == 2 * ax) {
                    if (i == 0) {
                        for (x in 0..2)
                            if (tracker[0][x] == 0)
                            {
                            tracker[0][x] = zero
                            buttonpressed[0][x]++
                        }
                    }
                    if (i == 1) {
                        for (x in 0..2)
                            if (tracker[1][x] == 0)
                            {
                            tracker[1][x] = zero
                            buttonpressed[1][x]++
                        }
                    }
                    if (i == 2) {
                        for (x in 0..2)
                            if (tracker[2][x] == 0) {
                            tracker[2][x] = zero
                            buttonpressed[2][x]++
                        }
                    }
                    if (i == 3) {
                        for (x in 0..2)
                            if (tracker[x][0] == 0) {
                            tracker[x][0] = zero
                            buttonpressed[x][0]++
                        }
                    }
                    if (i == 4) {
                        for (x in 0..2)
                            if (tracker[x][1] == 0) {
                            tracker[x][1] = zero
                            buttonpressed[x][1]++
                        }
                    }
                    if (i == 5) {
                        for (x in 0..2)
                            if (tracker[x][2] == 0) {
                            tracker[x][2] = zero
                            buttonpressed[x][2]++
                        }
                    }
                    if (i == 6) {
                        for (y in 0..2)
                            for (x in 0..2)
                                if (x == y)
                                    if (tracker[x][y] == 0)
                                    {
                                        tracker[x][y] = zero
                                        buttonpressed[x][y]++
                        }
                    }
                    if (i == 7) {
                        if (tracker[0][2] == 0) {
                            tracker[0][2] = zero
                            buttonpressed[0][2]++
                        }
                        else if (tracker[1][1] == 0) {
                            tracker[1][1] = zero
                            buttonpressed[1][1]++
                        }
                        else {
                            tracker[2][0] = zero
                            buttonpressed[2][0]++
                        }
                    }
                    return true
                }
                i++
            }
        }
        return false
    }

    fun emptycentre(): Boolean {
        if (impossible || hard) {
            if (tracker[1][1] == 0) {
                tracker[1][1] = zero
                buttonpressed[1][1]++
                return true
            }
        }
        return false
    }

    fun emptycorner(): Boolean {
        if (hard || impossible)
            if (tracker[0][0] + tracker[2][2] == 2 * ax || tracker[0][2] + tracker[2][0] == 2 * ax)
            {
            for (k in 0..2)
                for (j in 0..2)
                    if ((k + j) % 2 == 1)
                    {
                         if (tracker[k][j] == 0)
                             tracker[k][j] = zero
                        buttonpressed[k][j]++
                        return true
                    }
            }
        if (impossible)
            if (sum[6] == zero || sum[7] == zero) {
                if (sum[6] == zero) {
                    if (sum[0] + sum[3] > sum[2] + sum[5]) {
                        tracker[0][0] = zero
                        buttonpressed[0][0]++
                    }
                    else {
                    tracker[2][2] = zero
                    buttonpressed[2][2]++
                  }
                return true
            }
            if (sum[7] == zero) {
                if (sum[0] + sum[5] > sum[3] + sum[2]) {
                    tracker[0][2] = zero
                    buttonpressed[0][2]++
                } else {
                    tracker[2][0] = zero
                    buttonpressed[2][0]++
                }
                return true
            }
        }
        for (i in 0..2) {
            if (tracker[0][i] == ax) {
                if (tracker[0][0] == 0) {
                    tracker[0][0] = zero
                    buttonpressed[0][0]++
                    return true
                }
                if (tracker[0][2] == 0) {
                    tracker[0][2] = zero
                    buttonpressed[0][2]++
                    return true
                }
            }
        }
        for (i in 0..2) {
            if (tracker[2][i] == ax) {
                if (tracker[2][0] == 0) {
                    tracker[2][0] = zero
                    buttonpressed[2][0]++
                    return true
                }
                if (tracker[2][2] == 0) {
                    tracker[2][2] = zero
                    buttonpressed[2][2]++
                    return true
                }
            }
        }
        for (i in 0..2) {
            if (tracker[i][0] == ax) {
                if (tracker[0][0] == 0) {
                    tracker[0][0] = zero
                    buttonpressed[0][0]++
                    return true
                }
                if (tracker[2][0] == 0) {
                    tracker[2][0] = zero
                    buttonpressed[2][0]++
                    return true
                }
            }
        }
        for (i in 0..2) {
            if (tracker[i][2] == ax) {
                if (tracker[0][2] == 0) {
                    tracker[0][2] = zero
                    buttonpressed[0][2]++
                    return true
                }
                if (tracker[2][2] == 0) {
                    tracker[2][2] = zero
                    buttonpressed[2][2]++
                    return true
                }
            }
        }
        return false
    }

    fun emptyany() {
        if (ctrflag == 0) while (true) {
            val x = rand()
            val y = rand()
            if (tracker[x][y] == 0) {
                tracker[x][y] = zero
                buttonpressed[x][y]++
                return
            }
        }
        for (x in 0..2)
            for (y in 0..2)
                if (tracker[x][y] == 0) {
                     tracker[x][y] = zero
                     buttonpressed[x][y]++
                     return
                }
    }

    fun rand(): Int {
        return r.nextInt(3)
    }

    fun printBoard() {

        if (tracker[0][0] == 1) tzz.setImageResource(R.drawable.xscene)
        if (tracker[0][0] == 10) tzz.setImageResource(R.drawable.oscene)
        if (tracker[0][1] == 1) tzo.setImageResource(R.drawable.xscene)
        if (tracker[0][1] == 10) tzo.setImageResource(R.drawable.oscene)
        if (tracker[0][2] == 1) tzt.setImageResource(R.drawable.xscene)
        if (tracker[0][2] == 10) tzt.setImageResource(R.drawable.oscene)
        if (tracker[1][0] == 1) toz.setImageResource(R.drawable.xscene)
        if (tracker[1][0] == 10) toz.setImageResource(R.drawable.oscene)
        if (tracker[1][1] == 1) too.setImageResource(R.drawable.xscene)
        if (tracker[1][1] == 10) too.setImageResource(R.drawable.oscene)
        if (tracker[1][2] == 1) tot.setImageResource(R.drawable.xscene)
        if (tracker[1][2] == 10) tot.setImageResource(R.drawable.oscene)
        if (tracker[2][0] == 1) ttz.setImageResource(R.drawable.xscene)
        if (tracker[2][0] == 10) ttz.setImageResource(R.drawable.oscene)
        if (tracker[2][1] == 1) tto.setImageResource(R.drawable.xscene)
        if (tracker[2][1] == 10) tto.setImageResource(R.drawable.oscene)
        if (tracker[2][2] == 1) ttt.setImageResource(R.drawable.xscene)
        if (tracker[2][2] == 10) ttt.setImageResource(R.drawable.oscene)
        resetchecker++
    }

    fun winchecker() {
        ctrflag++
        sum[0] = tracker[0][0] + tracker[0][1] + tracker[0][2]
        sum[1] = tracker[1][0] + tracker[1][1] + tracker[1][2]
        sum[2] = tracker[2][0] + tracker[2][1] + tracker[2][2]
        sum[3] = tracker[0][0] + tracker[1][0] + tracker[2][0]
        sum[4] = tracker[0][1] + tracker[1][1] + tracker[2][1]
        sum[5] = tracker[0][2] + tracker[1][2] + tracker[2][2]
        sum[6] = tracker[0][0] + tracker[1][1] + tracker[2][2]
        sum[7] = tracker[0][2] + tracker[1][1] + tracker[2][0]

        val wins = BooleanArray(1)
        val returns = BooleanArray(1)
        currentgamedonechecker++
        resetchecker++
        for (i in 0..7)
            if (sum[i] == 3 || sum[i] == 30) {
                 if (Vibration) {
                 // Get instance of Vibrator from current Context
                     val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                        // Vibrate for 400 milliseconds
                    v.vibrate(400)
                 }
            win++
            if (sum[i] == 3 && ax == 1) {
                score1++
//                val q1 = findViewById<View>(R.id.p1score) as TextView
                wins[0] = true
                returns[0] = false
                if (wins[0]) {
                    p1score.setTextColor(resources.getColor(R.color.cotextred))
                    wins[0] = false
                    returns[0] = true
                }
                val finalReturns = returns[0]
                Handler().postDelayed({
                    if (finalReturns) {
                        p1score.setTextColor(resources.getColor(R.color.cotext))
                        wins[0] = false
                        returns[0] = true
                    }
                }, 300)
                p1score.text = "" + score1
                // showDialog("" + player1 + " won!", "" + score1, "" + player2, "" + score2);
                //   Toast.makeText(getApplicationContext(),player1 + " won!", Toast.LENGTH_SHORT).show();
                Handler().postDelayed({ playmore() }, 500)
            }
            if (sum[i] == 3 && zero == 1) {
                score2++
//                val q1 = findViewById<View>(R.id.p2score) as TextView
                wins[0] = true
                returns[0] = false
                if (wins[0]) {
                    p2score.setTextColor(resources.getColor(R.color.cotextred))
                    wins[0] = false
                    returns[0] = true
                }
                val finalReturns = returns[0]
                Handler().postDelayed({
                    if (finalReturns) {
                        p2score.setTextColor(resources.getColor(R.color.cotext))
                        wins[0] = false
                        returns[0] = true
                    }
                }, 500)
                p2score.text = "" + score2
                // showDialog("" + player2 + " won!", "" + score2, "" + player1, "" + score1);
                //  Toast.makeText(getApplicationContext(),"AI won!", Toast.LENGTH_SHORT).show();
                Handler().postDelayed({ playmore() }, 300)
            }
            if (sum[i] == 30 && ax == 10) {
                score1++
//                val q1 = findViewById<View>(R.id.p1score) as TextView
                wins[0] = true
                returns[0] = false
                if (wins[0]) {
                    p1score.setTextColor(resources.getColor(R.color.cotextred))
                    wins[0] = false
                    returns[0] = true
                }
                val finalReturns = returns[0]
                Handler().postDelayed({
                    if (finalReturns) {
                        p1score.setTextColor(resources.getColor(R.color.cotext))
                        wins[0] = false
                        returns[0] = true
                    }
                }, 300)
                p1score.text = "" + score1
                //    showDialog("" + player1 + " won!", "" + score1, "" + player2, "" + score2);
                //  Toast.makeText(getApplicationContext(),player1 + " won!", Toast.LENGTH_SHORT).show();
                Handler().postDelayed({ playmore() }, 500)
            }
            if (sum[i] == 30 && zero == 10) {
                score2++
//                val q1 = findViewById<View>(R.id.p2score) as TextView
                wins[0] = true
                returns[0] = false
                if (wins[0]) {
                    p2score.setTextColor(resources.getColor(R.color.cotextred))
                    wins[0] = false
                    returns[0] = true
                }
                val finalReturns = returns[0]
                Handler().postDelayed({
                    if (finalReturns) {
                        p2score.setTextColor(resources.getColor(R.color.cotext))
                        wins[0] = false
                        returns[0] = true
                    }
                }, 300)
                p2score.text = "" + score2
                // to asi netreba
                //   Toast.makeText(getApplicationContext(), " AI won!", Toast.LENGTH_SHORT).show();
                Handler().postDelayed({ playmore() }, 500)


                //  showDialog("" + player2 + " won!", "" + score2, "" + player1, "" + score1);
            }
        }
        if (ctrflag == 9 && win == 0) {
            //  showDialog("This is a draw !", "" + score1, "" + "AI", "" + score2);
            Toast.makeText(applicationContext, "DRAW!", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ playmore() }, 900)
            drawchecker++
        }
    } //end winchecker()

    private fun playmore() {
        val max = 4
        val min = 1
        if (savedValue == 0 || savedValue == 5) {
            val random = Random().nextInt(max - min + 1) + min
            if (random == 1) {
                easy = true
                medium = false
                hard = false
                impossible = false
            }
            if (random == 2) {
                easy = false
                medium = true
                hard = false
                impossible = false
            }
            if (random == 3) {
                easy = false
                medium = false
                hard = true
                impossible = false
            }
            if (random == 4) {
                easy = false
                medium = false
                hard = false
                impossible = true
            }
        }
        if (savedValue == 1) {
            easy = true
            medium = false
            hard = false
            impossible = false
        }
        if (savedValue == 2) {
            easy = false
            medium = true
            hard = false
            impossible = false
        }
        if (savedValue == 3) {
            easy = false
            medium = false
            hard = true
            impossible = false
        }
        if (savedValue == 4) {
            easy = false
            medium = false
            hard = false
            impossible = true
        }
        if (drawchecker > 0 || win > 0) {
            game++
            //    TextView qq = (TextView) findViewById(R.id.gamenumber);
            //  qq.setText("" + game);
            for (i in 0..7)
                sum[i] = 0
            drawchecker = 0

            tzz.setImageDrawable(null)
            tzo.setImageDrawable(null)
            tzt.setImageDrawable(null)
            toz.setImageDrawable(null)
            too.setImageDrawable(null)
            tot.setImageDrawable(null)
            ttz.setImageDrawable(null)
            tto.setImageDrawable(null)
            ttt.setImageDrawable(null)

            for (i in 0..2)
                for (j in 0..2)
                    buttonpressed[i][j] = 0
            for (i in 0..2)
                for (j in 0..2)
                    tracker[i][j] = 0

//ToTo som ja dal prec
            if (!selectedsingleplayer) {
                if ((game + 1) % 2 == 0) {
                    Toast.makeText(this, "$player1\'s turn", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(this, "$player2\'s turn", Toast.LENGTH_SHORT).show()
            }
            win = 0
            summ = 0
            ctrflag = 0
            flag = (game + 1) % 2
            currentgamedonechecker = 0
            if (selectedsingleplayer && game % 2 == 0)
                cpuplay()
        }
    }

    fun doreset() {

        //  TextView qq = (TextView) findViewById(R.id.gamenumber);
        // qq.setText("" + 1);
        for (i in 0..2)
            for (j in 0..2)
                tracker[i][j] = 0
        for (i in 0..2)
            for (j in 0..2)
                buttonpressed[i][j] = 0

        tzz.setImageDrawable(null)
        tzo.setImageDrawable(null)
        tzt.setImageDrawable(null)
        toz.setImageDrawable(null)
        too.setImageDrawable(null)
        tot.setImageDrawable(null)
        ttz.setImageDrawable(null)
        tto.setImageDrawable(null)
        ttt.setImageDrawable(null)

        win = 0
        drawchecker = 0
        summ = 0
        resetchecker = 0
        ctrflag = 0
        score1 = 0
        score2 = 0
        game = 1
        flag = 0
        currentgamedonechecker = 0

        p1score.text = "" + score1
        p2score.text = "" + score2
        Toast.makeText(this, "$player1\'s turn", Toast.LENGTH_SHORT).show()
    }


    private fun showExitDialog() {
        val dialog = Dialog(this@SceneActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_layout_exit)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.show()
        val exit =
            dialog.findViewById<Button>(R.id.yes_button)
        val dismiss =
            dialog.findViewById<Button>(R.id.no_button)
        exit.setOnClickListener {
            doreset()
            finish()
            val intent = Intent(this@SceneActivity, MainActivity::class.java)
            startActivity(intent)
        }
        dismiss.setOnClickListener { dialog.dismiss() }
    }

    fun presentActivity(view: View) {
        val options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "transition")
//        val revealX = (view.x + view.width / 2).toInt()
//        val revealY = (view.y + view.height / 2).toInt()
        val intent = Intent(this, SettingActivity::class.java)
//        intent.putExtra(SettingActivity.EXTRA_CIRCULAR_REVEAL_X, revealX)
//        intent.putExtra(SettingActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY)
        ActivityCompat.startActivity(this, intent, options.toBundle())
    }

    override fun onBackPressed() {
        showExitDialog()
    }

}