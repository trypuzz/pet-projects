package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import com.example.notes.constance.Constance

class MakeMoneyActivity : AppCompatActivity() {

    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_money)

        handler.postDelayed({
            startActivity(Intent(this, ChatActivity::class.java))
            finish()
        }, Constance.TIME_SLIDE_ACTIVITY)

        val buttonSkip3 = findViewById<Button>(R.id.btn_skip3)
        buttonSkip3.setOnClickListener(::onClickSkip3)
    }

    private fun onClickSkip3(view: View) {
        startActivity(Intent(this, ChatActivity::class.java))
        handler.removeCallbacksAndMessages(null);
    }

}
