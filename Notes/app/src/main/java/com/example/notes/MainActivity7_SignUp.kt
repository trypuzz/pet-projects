
package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity7_SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7_signup)

        val buttonLogin = findViewById<Button>(R.id.btn_loginLauncher)
        buttonLogin.setOnClickListener{
            startActivity(Intent(this, MainActivity8_LogIn::class.java))
        }
    }
}