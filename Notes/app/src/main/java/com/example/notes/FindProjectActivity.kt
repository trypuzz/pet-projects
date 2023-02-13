package com.example.notes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.notes.constance.Constance

class FindProjectActivity : AppCompatActivity() {

    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_project)

        handler.postDelayed({
            startActivity(Intent(this, MakeMoneyActivity::class.java))
            finish()
        }, Constance.TIME_SLIDE_ACTIVITY)

        val buttonSkip2 = findViewById<Button>(R.id.btn_skip2)
        buttonSkip2.setOnClickListener(::onClickSkip2)
    }

    private fun onClickSkip2(view: View) {
        startActivity(Intent(this, MakeMoneyActivity::class.java))
        handler.removeCallbacksAndMessages(null);
    }
}