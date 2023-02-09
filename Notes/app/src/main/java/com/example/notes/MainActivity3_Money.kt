package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button

class MainActivity3_Money : AppCompatActivity() {

    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3_money)

        handler.postDelayed({
            startActivity(Intent(this, MainActivity4_Phone::class.java))
            finish()
        }, 10000)

        val buttonSkip3 = findViewById<Button>(R.id.btn_skip3)
        buttonSkip3.setOnClickListener(::onClickSkip3)
    }

    private fun onClickSkip3(view: View) {
        startActivity(Intent(this, MainActivity4_Phone::class.java))
        handler.removeCallbacksAndMessages(null);
    }

}