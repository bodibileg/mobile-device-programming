package com.example.survey

data class SurveyQuestion(
    val title: String,
    val options: List<String>,
    var selectedOption: String? = null
)