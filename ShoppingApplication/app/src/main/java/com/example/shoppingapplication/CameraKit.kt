package com.example.shoppingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CameraKit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_kit)
        val key = intent.getStringExtra("key")

        var textView = findViewById<TextView>(R.id.textView)
        textView.setText(key)
    }
}