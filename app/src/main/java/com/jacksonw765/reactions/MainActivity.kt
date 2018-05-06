package com.jacksonw765.reactions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import java.util.*
import android.util.DisplayMetrics
import android.view.View
import android.widget.*
import android.media.MediaPlayer




class MainActivity : AppCompatActivity() {

    lateinit var buttonCoin: ImageView
    lateinit var buttonGoomba: ImageView
    lateinit var layout: RelativeLayout
    lateinit var random: Random
    lateinit var textTimerUI: TextView
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

        //find all resources from xml
        layout = findViewById(R.id.layout)
        buttonCoin = findViewById(R.id.imageView)
        textScore = findViewById(R.id.textScore)
        textCountDown = findViewById(R.id.textCountdown)
        buttonGoomba = findViewById(R.id.buttonGoomba)
        textTimerUI = findViewById(R.id.textMainTimer)
        random = Random()

        //get window manager to get the display resolution
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        //get screen resolution
        displayX = displayMetrics.widthPixels
        displayY = displayMetrics.heightPixels

        textTimerUI.text = "0"

        //give high low values for random gen
        heightLow = displayY * .18
        withLow =  displayX * .15

        //place the buttonCoin off screen
        buttonCoin.x = -1000f
        buttonCoin.y = -1000f
        buttonGoomba.x = -1000f
        buttonGoomba.y = -1000f

        setupTimer()
    }

    private fun startGame() {
            buttonCoin.x = random.nextInt((displayMetrics.widthPixels)/2).toFloat()
            buttonCoin.y = random.nextInt((displayMetrics.heightPixels)/2).toFloat()
            println("X is: " + buttonCoin.x)
            println("Y is: " + buttonCoin.y)

            buttonCoin.setOnClickListener(object: View.OnClickListener {
                override fun onClick(view: View?) {
                    ++score
                    buttonCoin.x = generateRandomX()
                    buttonCoin.y = generateRandomY()
                    textScore.text = ""+score

                    //if button goomba is off screen
                    if(buttonGoomba.x != -1000f) {
                        buttonGoomba.x = -1000f
                        buttonGoomba.y = -1000f
                    }
                    // 25% chance of generating enemy
                    if((random.nextInt(4)+1) == 1) {
                        generateEnemy()
                    }
                }
            })
    }

    //generate enemy
    private fun generateEnemy() {
            buttonGoomba.x = generateRandomX()
            buttonGoomba.y = generateRandomY()
            buttonGoomba.setOnClickListener(object: View.OnClickListener {
                override fun onClick(p0: View?) {
                    score -= 5
                    buttonGoomba.x = -1000f
                    buttonGoomba.y = -1000f
                    textScore.text = ""+score
                }
            } )
    }

    private fun generateRandomX(): Float {
        return random.nextInt((displayX - withLow.toInt())) + withLow.toFloat()
    }

    private fun generateRandomY(): Float {
        return random.nextInt((displayY - heightLow.toInt())) + withLow.toFloat()
    }

    private fun setupTimer() {
        val mp = MediaPlayer.create(applicationContext, R.raw.countdown)
        val delay: Long = 4000
        val period: Long = 1000
        val timer = object : CountDownTimer(delay, period) {
                override fun onFinish() {
                    textCountDown.text = "Go!"
                    startGameTimer()
                }
                override fun onTick(millisUntilFinished: Long) {
                    println("OnTickSetupTimer!!!!!")
                    if(millisUntilFinished < 1000) {

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
        val timer2 = object : CountDownTimer(delay, period) {
            override fun onFinish() {
                isGameStarted = false
            }

            override fun onTick(untilFinished: Long) {
                println("OnTickGameTimer!***********")
                textTimerUI.text = "" + (untilFinished/1000).toInt()
            }
        }
        textTimerUI.text = "25"
        timer2.start()
        isGameStarted = true
        startGame()
    }

}
