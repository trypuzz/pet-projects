package com.example.notes


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button

class MainActivity6_Heart : AppCompatActivity() {

    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6_heart)

        handler.postDelayed({
            startActivity(Intent(this, MainActivity7_SignUp::class.java))
            finish()
        }, 10000)

        val buttonSkip6 = findViewById<Button>(R.id.btn_skip6)
        buttonSkip6.setOnClickListener(::onClickSkip6)
    }


    private fun onClickSkip6(view: View) {
        startActivity(Intent(this, MainActivity7_SignUp::class.java))
        handler.removeCallbacksAndMessages(null);
    }
}