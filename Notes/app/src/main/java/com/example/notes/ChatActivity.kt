package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import com.example.notes.constance.Constance

class ChatActivity : AppCompatActivity() {

    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        handler.postDelayed({
            startActivity(Intent(this, WorkHardActivity::class.java))
            finish()
        }, Constance.TIME_SLIDE_ACTIVITY)

        val buttonSkip4 = findViewById<Button>(R.id.btn_skip4)
        buttonSkip4.setOnClickListener(::onClickSkip4)
    }

    private fun onClickSkip4(view: View) {
        startActivity(Intent(this, WorkHardActivity::class.java))
        handler.removeCallbacksAndMessages(null);
    }
}
