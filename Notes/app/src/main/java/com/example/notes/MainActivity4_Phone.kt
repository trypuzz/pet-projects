package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button

class MainActivity4_Phone : AppCompatActivity() {

    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4_phone)

        handler.postDelayed({
            startActivity(Intent(this, MainActivity5_Arrow::class.java))
            finish()
        }, Constance.TIME_SLIDE_ACTIVITY)

        val buttonSkip4 = findViewById<Button>(R.id.btn_skip4)
        buttonSkip4.setOnClickListener(::onClickSkip4)
    }

    private fun onClickSkip4(view: View) {
        startActivity(Intent(this, MainActivity5_Arrow::class.java))
        handler.removeCallbacksAndMessages(null);
    }
}