package com.example.shoppingapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val value = "TextViewToCheck"
        val intent = Intent(this, CameraKit::class.java)
        intent.putExtra("key", value)

        button.setOnClickListener {
            startActivity(intent)
        }
    }


}