package com.example.triviaquiz.ui.questions.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaquiz.R
import com.example.triviaquiz.models.Result
import com.example.triviaquiz.util.common.conditionalVisibility
import com.example.triviaquiz.util.common.toDecodedURL
import com.example.triviaquiz.util.common.transformToAnswer
import kotlinx.android.synthetic.main.card_view_questions.view.*

class QuestionAdapter(
    private val questions: MutableList<Result>
) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    private var currentPosition = 0
    private lateinit var answerAdapter: AnswerAdapter
    private val answered: MutableList<Boolean?> = questions.map { null }.toMutableList()

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewQuestion: TextView = itemView.textViewQuestion
        val linearLayoutBooleanType: LinearLayout = itemView.linearLayoutBooleanType
        val linearLayoutMultipleType: LinearLayout = itemView.linearLayoutMultipleType
        val buttonTrueAnswer: Button = itemView.buttonTrueAnswer
        val buttonFalseAnswer: Button = itemView.buttonFalseAnswer
        val recyclerViewAnswer: RecyclerView = itemView.recyclerViewAnswer
    }

    internal fun getAnswers(): List<Boolean?> {
        return answered.toList()
    }

    fun setAnswer(position: Int, value: Boolean) {
        answered[position] = value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_questions, parent, false)
        return QuestionViewHolder(itemView)
    }

    override fun getItemCount(): Int = questions.size

    private fun initializeAnswer(holder: QuestionViewHolder) {
        answerAdapter = AnswerAdapter(this)
        holder.recyclerViewAnswer.apply {
            setHasFixedSize(true)
            adapter = answerAdapter
            layoutManager = LinearLayoutManager(holder.itemView.context)
        }
        if (!answerAdapter.isAlreadyLoaded()) {
            answerAdapter.setAnswersAndCorrectAnswer(
                currentPosition,
                questions[currentPosition].correctAnswer,
                questions[currentPosition].incorrectAnswers.transformToAnswer()
            )
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        currentPosition = position
        with(holder) {
            textViewQuestion.text = question.question.toDecodedURL()
            linearLayoutMultipleType.conditionalVisibility(question.type == "multiple")
            linearLayoutBooleanType.conditionalVisibility(question.type == "boolean")
            buttonTrueAnswer.setOnClickListener {
                setAnswer(position, question.correctAnswer == "True")
            }
            buttonFalseAnswer.setOnClickListener {
                setAnswer(position, question.correctAnswer == "False")
            }
            initializeAnswer(this)
        }
    }

}