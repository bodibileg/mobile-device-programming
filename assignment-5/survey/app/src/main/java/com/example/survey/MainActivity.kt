package com.example.survey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val foodRadio: RadioButton = findViewById(R.id.food_radio)
        val dietRadio: RadioButton = findViewById(R.id.diet_radio)
        val startSurveyButton: Button = findViewById(R.id.start_survey_button)

        startSurveyButton.setOnClickListener {
            val intent = Intent(this, SurveyActivity::class.java)
            if (foodRadio.isChecked) {
                intent.putExtra("surveyType", "foodPreferences")
            } else if (dietRadio.isChecked) {
                intent.putExtra("surveyType", "dietaryHabits")
            }
            startActivity(intent)
        }
    }
}