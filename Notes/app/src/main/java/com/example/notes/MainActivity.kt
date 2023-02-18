package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart = findViewById<Button>(R.id.button_start)
        buttonStart.setOnClickListener{
            startActivity(Intent(this, FindProjectActivity::class.java))
        }
        val buttonLogin = findViewById<Button>(R.id.btn_loginLauncher)
        buttonLogin.setOnClickListener{
            startActivity(Intent(this, LogInActivity::class.java))
        }
    }
}
