package com.example.triviaquiz.ui.questions

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaquiz.R
import com.example.triviaquiz.models.Questions
import com.example.triviaquiz.models.Result
import com.example.triviaquiz.ui.questions.adapter.QuestionAdapter
import com.example.triviaquiz.ui.result.ResultActivity
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    private lateinit var questionRecyclerView: RecyclerView
    private lateinit var questionAdapter: QuestionAdapter

    private fun initUserAdapter(result: List<Result>) {
        questionAdapter = QuestionAdapter(result.toMutableList())
        questionRecyclerView = findViewById(R.id.recyclerViewQuestion)
        questionRecyclerView.apply {
            setHasFixedSize(true)
            adapter = questionAdapter
            layoutManager = LinearLayoutManager(this@QuestionActivity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        initUserAdapter(
            intent.getParcelableExtra<Questions>("Questions")?.results!!
        )

        buttonFinishQuiz.setOnClickListener {
            val answers: ArrayList<String> = arrayListOf()
            questionAdapter.getAnswers().map { it.toString() }.forEach {
                answers.add(it)
            }

            val intent = Intent(this@QuestionActivity, ResultActivity::class.java)
            intent.putStringArrayListExtra("Answers", answers)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}