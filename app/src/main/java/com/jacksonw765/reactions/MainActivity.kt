package com.jacksonw765.reactions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Looper
import java.util.*
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.transition.Fade
import android.widget.*
import android.R.raw
import android.media.MediaPlayer




class MainActivity : AppCompatActivity() {

    lateinit var button: ImageView
    lateinit var layout: RelativeLayout
    lateinit var random: Random
    lateinit var textScore: TextView
    lateinit var textCountDown: TextView

    val displayMetrics = DisplayMetrics()

    var isGameStarted: Boolean = false
    var score: Int = 0
    var displayX = -1
    var displayY = -1

    var heightLow = -1.0
    var withLow = -1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_main)

        layout = findViewById(R.id.layout)
        button = findViewById(R.id.imageView)
        textScore = findViewById(R.id.textScore)
        textCountDown = findViewById(R.id.textCountdown)
        random = Random()

        windowManager.defaultDisplay.getMetrics(displayMetrics)

        displayX = displayMetrics.widthPixels
        displayY = displayMetrics.heightPixels

        heightLow = displayY * .18
        withLow =  displayX * .15

        button.x = -1000f
        button.y = -1000f

        setupTimer()
    }

    private fun startGame() {
        //while(isGameStarted) {
            button.x = random.nextInt((displayMetrics.widthPixels)/2).toFloat()
            button.y = random.nextInt((displayMetrics.heightPixels)/2).toFloat()
            println("X is: " + button.x)
            println("Y is: " + button.y)

            button.setOnClickListener(object: View.OnClickListener {
                override fun onClick(view: View?) {
                    ++score
                    var randomx = random.nextInt((displayX - withLow.toInt())) + withLow.toFloat()
                    var randomy = random.nextInt((displayY - heightLow.toInt())) + withLow.toFloat()
                    println("*****$randomx")
                    println("*****$randomy")
                    button.x = randomx
                    button.y = randomy
                    println("X is: " + button.x)
                    println("Y is: " + button.y)
                    textScore.text = ""+score

                }
            })
        //}
    }

    private fun setupTimer() {
        val mp = MediaPlayer.create(applicationContext, R.raw.countdown)
        val delay: Long = 4000
        val period: Long = 1000
        val timer = object : CountDownTimer(delay, period) {
                override fun onFinish() {
                    startGameTimer()
                }

                override fun onTick(millisUntilFinished: Long) {
                    if(millisUntilFinished < 1000) {
                        textCountDown.text = ""+0
                    }
                    textCountDown.text = ""+(millisUntilFinished/1000).toInt()
                }
            }
        timer.start()
        mp.start()
    }

    private fun startGameTimer() {
        val delay: Long = 1000
        val period: Long = 25000
        val timer = object : CountDownTimer(delay, period) {
            override fun onFinish() {
                isGameStarted = false
            }

            override fun onTick(untilFinished: Long) {
                textCountDown.text = "" + untilFinished

            }
        }
        timer.start()
        isGameStarted = true
        startGame()
    }

}
