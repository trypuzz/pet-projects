package com.example.triviaquiz.ui.result

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.triviaquiz.R
import com.example.triviaquiz.ui.mainmenu.MainMenuActivity
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    private fun calculateScore(answers: ArrayList<String>) {
        val rightAnswers = answers.filter { it == "true" }
        val wrongAnswers = answers.filter { it == "false" }
        val total = (rightAnswers.size * 100) / answers.size
        textViewScore.text = total.toString()
        textViewDescription.text =
            "Right/Wrong ${rightAnswers.size}/${wrongAnswers.size} from ${answers.size}"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        intent.getStringArrayListExtra("Answers").apply {
            calculateScore(this!!)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@ResultActivity, MainMenuActivity::class.java))
    }
}