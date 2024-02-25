package com.snap.camerakit.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val button = findViewById<Button>(R.id.button)
        val etNumber = findViewById<EditText>(R.id.editTextNum)

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("etNumber", etNumber.text.toString())
            startActivity(intent)
        }
    }
}