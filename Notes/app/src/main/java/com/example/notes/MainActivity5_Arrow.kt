package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button

class MainActivity5_Arrow : AppCompatActivity() {

    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5_arrow)

        handler.postDelayed({
            startActivity(Intent(this, MainActivity6_Heart::class.java))
            finish()
        }, 10000)

        val buttonSkip5 = findViewById<Button>(R.id.btn_skip5)
        buttonSkip5.setOnClickListener(::onClickSkip5)
    }


    private fun onClickSkip5(view: View) {
        startActivity(Intent(this, MainActivity6_Heart::class.java))
        handler.removeCallbacksAndMessages(null);
    }
}