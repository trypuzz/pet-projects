package com.example.triviaquiz.ui.mainmenu

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.triviaquiz.R
import com.example.triviaquiz.models.Category
import com.example.triviaquiz.ui.questions.QuestionActivity
import com.example.triviaquiz.util.common.createLoading
import com.example.triviaquiz.util.common.onTextChange
import com.example.triviaquiz.util.common.setupSpinner
import com.example.triviaquiz.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main_menu.*
import java.util.*
import javax.inject.Inject

class MainMenuActivity : DaggerAppCompatActivity() {

    private lateinit var questionTriviaViewModel: QuestionTriviaViewModel
    private lateinit var loadingDialog: Dialog

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private val categories = arrayOf(
        Category("9:General"),
        Category("18:Computer"),
        Category("21:Sports"),
        Category("23:History"),
        Category("27:Animals")
    )

    private val difficulties = arrayOf(
        "Easy",
        "Medium",
        "Hard"
    )

    private val types = arrayOf(
        "boolean",
        "multiple"
    )

    private fun initUI() {
        loadingDialog = createLoading()

        editTextAmountOfQuiz.onTextChange { amount ->
            try {
                questionTriviaViewModel.amount.set(amount.toInt())
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
        }

        spinnerCategory.setupSpinner(
            this@MainMenuActivity,
            categories,
            nothingSelected = {},
            itemSelected = { position ->
                questionTriviaViewModel.category
                    .set(categories[position].categoryName.split(":")[0].toInt())
            }
        )

        spinnerDifficulty.setupSpinner(
            this@MainMenuActivity,
            difficulties,
            nothingSelected = {},
            itemSelected = { position ->
                questionTriviaViewModel.difficulty
                    .set(difficulties[position].toLowerCase(Locale.ROOT))
            }
        )

        spinnerType.setupSpinner(
            this@MainMenuActivity,
            types,
            nothingSelected = {},
            itemSelected = { position ->
                questionTriviaViewModel.type
                    .set(types[position].toLowerCase(Locale.ROOT))
            }
        )

        buttonStartQuiz.setOnClickListener {
            questionTriviaViewModel.apply {
                if (
                    !type.get().isNullOrEmpty() &&
                    !difficulty.get().isNullOrEmpty() &&
                    category.get() != 0 && amount.get()!! < 51
                ) {
                    questionTriviaViewModel.retrieveQuestionTrivia()
                } else {
                    Toast.makeText(
                        this@MainMenuActivity,
                        getString(R.string.start_quiz_invalid_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionTriviaViewModel = ViewModelProviders
            .of(this, providerFactory)
            .get(QuestionTriviaViewModel::class.java)

        setContentView(R.layout.activity_main_menu)

        initUI()

        // loading trigger
        questionTriviaViewModel.isLoading.observe(this, Observer { loading ->
            if (loading) loadingDialog.show()
            else loadingDialog.dismiss()
        })

        questionTriviaViewModel.questionTriviaSuccess.observe(
            this@MainMenuActivity,
            Observer { questions ->
                if (questions == null) return@Observer
                else if (questions.results.isNotEmpty()) startActivity(
                    Intent(
                        this@MainMenuActivity,
                        QuestionActivity::class.java
                    ).putExtra("Questions", questions)
                )
            })
    }
}