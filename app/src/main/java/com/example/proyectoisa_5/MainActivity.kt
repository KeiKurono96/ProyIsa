package com.example.proyectoisa_5

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnEmpezar.setOnClickListener {
            val intent = Intent(this,com.example.proyectoisa_5.java.login::class.java)
            //var anima = AnimationUtils.loadAnimation(this,R.anim.bounce)
            //btnEmpezar.startAnimation(anima)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }
    }
}