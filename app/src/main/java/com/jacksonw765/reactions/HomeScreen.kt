package com.jacksonw765.reactions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.widget.Button
import android.transition.Slide
import android.view.View
import android.app.ActivityOptions
import android.content.Intent
import android.transition.Scene
import android.transition.TransitionManager
import android.view.ViewGroup


class HomeScreen : AppCompatActivity() {

    lateinit var buttonSettings: Button
    lateinit var buttonStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_home_screen)
        buttonStart = findViewById(R.id.buttonStart)

        buttonStart.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                val i = Intent(applicationContext, MainActivity::class.java)
               startActivity(i)
            }
        })
    }
}
