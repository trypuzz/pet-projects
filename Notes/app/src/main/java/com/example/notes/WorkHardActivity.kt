package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import com.example.notes.constance.Constance

class WorkHardActivity : AppCompatActivity() {

    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_hard)

        handler.postDelayed({
            startActivity(Intent(this, EnjoyActivity::class.java))
            finish()
        }, Constance.TIME_SLIDE_ACTIVITY)

        val buttonSkip5 = findViewById<Button>(R.id.btn_skip5)
        buttonSkip5.setOnClickListener(::onClickSkip5)
    }

    private fun onClickSkip5(view: View) {
        startActivity(Intent(this, EnjoyActivity::class.java))
        handler.removeCallbacksAndMessages(null);
    }

}
