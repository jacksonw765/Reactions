package com.jacksonw765.reactions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import java.util.*
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    lateinit var button: ImageView
    lateinit var layout: RelativeLayout
    lateinit var random: Random
    lateinit var textScore: TextView
    var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        supportActionBar!!.hide()

        setContentView(R.layout.activity_main)


        layout = findViewById(R.id.layout)
        button = findViewById(R.id.imageView)
        textScore = findViewById(R.id.textScore)
        random = Random()
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        //displayMetrics.

        val displayX = displayMetrics.widthPixels
        val displayY = displayMetrics.heightPixels
        println(displayX)
        println(displayY)

        val heightLow = displayY * .18
        val withLow = displayX * .12

        button.x = random.nextInt((displayMetrics.widthPixels)/2).toFloat()
        button.y = random.nextInt((displayMetrics.heightPixels)/2).toFloat()
        println("X is: " + button.x)
        println("Y is: " + button.y)



        button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                ++score
                button.x = random.nextInt((displayX - withLow.toInt())) + withLow.toFloat()
                button.y = random.nextInt((displayY - heightLow.toInt())) + withLow.toFloat()
                println("X is: " + button.x)
                println("Y is: " + button.y)
                textScore.text = ""+score

            }

        })
    }
}
