package com.example.converter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.widget.Toast

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        fun toastMe() {
            val myToast = Toast.makeText(this, "Welcome Back!", Toast.LENGTH_SHORT)
            myToast.setGravity(Gravity.TOP, 0, 0)
            myToast.show()
        }
        toastMe()

        Handler().postDelayed(
            {
                val intent = Intent( this@splash,MainActivity :: class.java)
                startActivity(intent)
                finish()
            },3000
        )


    }
}