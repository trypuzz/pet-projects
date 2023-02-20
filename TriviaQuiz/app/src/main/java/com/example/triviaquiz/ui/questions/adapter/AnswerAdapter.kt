package com.example.triviaquiz.ui.questions.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaquiz.R
import com.example.triviaquiz.models.Answer
import com.example.triviaquiz.util.common.toDecodedURL
import kotlinx.android.synthetic.main.card_view_answer.view.*

class AnswerAdapter(
    private val questionAdapter: QuestionAdapter
) : RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

    private val answers = mutableListOf<Answer>()
    private val correctAnswer = ObservableField<String>()
    private val questionPosition = ObservableField<Int>()

    internal fun isAlreadyLoaded(): Boolean {
        return answers.isNotEmpty() && !correctAnswer.get().isNullOrEmpty()
    }

    internal fun setAnswersAndCorrectAnswer(position: Int, correct: String, answers: List<Answer>) {
        this.answers.apply {
            addAll(answers)
            add(Answer(correct, false))
            random()
            correctAnswer.set(correct)
            questionPosition.set(position)
        }
        notifyDataSetChanged()
    }

    private fun selectAnswer(answerItem: Answer) {
        answers.filter { ans -> ans.isSelected }.map { ans -> ans.isSelected = false }
        answerItem.isSelected = true
        questionAdapter.setAnswer(
            questionPosition.get()!!,
            answerItem.answer == correctAnswer.get()
        )
        notifyDataSetChanged()
    }

    inner class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val constraintLayoutAnswer: ConstraintLayout = itemView.constraintLayoutAnswer
        val textViewAnswerTitle: TextView = itemView.textViewAnswerTitle
        val checkBoxAnswerSelected: CheckBox = itemView.checkBoxAnswerSelected
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_answer, parent, false)
        return AnswerViewHolder(itemView)
    }

    override fun getItemCount(): Int = answers.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val answerItem = answers[position]
        with(holder) {
            textViewAnswerTitle.text = answerItem.answer.toDecodedURL()
            checkBoxAnswerSelected.isChecked = answerItem.isSelected
            constraintLayoutAnswer.setOnClickListener {
                selectAnswer(answerItem)
            }
        }
    }

}