package com.example.survey

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.survey.databinding.ActivitySurveyBinding

class SurveyActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var surveyTitleTextView: TextView
    private lateinit var submitButton: Button
    private lateinit var questionContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        recyclerView = findViewById(R.id.recyclerViewQuestions)
        surveyTitleTextView = findViewById(R.id.textViewSurveyTitle)
        submitButton = findViewById(R.id.buttonSubmitSurvey)
        questionContainer = findViewById(R.id.questionContainer)

        val surveyType = intent.getStringExtra("surveyType")

        surveyTitleTextView.text = if (surveyType == "foodPreferences") {
            "Food Preferences Survey"
        } else {
            "Dietary Habits Survey"
        }

        // Initialize RecyclerView and Adapter
        questionAdapter = QuestionAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SurveyActivity)
            adapter = questionAdapter
        }

        when (surveyType) {
            "foodPreferences" -> questionAdapter.setQuestions(Questions.foodPreferencesQuestions)
            "dietaryHabits" -> questionAdapter.setQuestions(Questions.dietaryHabitsQuestions)
        }

        submitButton.setOnClickListener {
            displaySelectedQuestionsWithAnswers()
        }
    }

    private fun displaySelectedQuestionsWithAnswers() {
        val selectedOptions = questionAdapter.getSelectedOptions()

        // Clear previous views if any
        questionContainer.removeAllViews()

        // Display selected questions with answers
        for ((question, answer) in selectedOptions) {
            val textViewQuestion = TextView(this)
            textViewQuestion.text = "${question.title}: $answer"
            questionContainer.addView(textViewQuestion)
        }
    }
}