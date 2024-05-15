package com.example.survey

object Questions {
    val foodPreferencesQuestions = listOf(
        SurveyQuestion(
            "What is your favorite cuisine?",
            listOf("Chinese", "French", "Italian", "Indian", "Japanese", "Thai", "Turkish")
        ),
        SurveyQuestion(
            "How often do you eat out?",
            listOf("Never", "Rarely", "Sometimes", "Frequently")
        ),
        SurveyQuestion("Do you prefer spicy food?", listOf("Yes", "No")),
        SurveyQuestion("Do you prefer vegetarian food?", listOf("Yes", "No")),
        SurveyQuestion("Do you like seafood?", listOf("Yes", "No"))
    )

    val dietaryHabitsQuestions = listOf(
        SurveyQuestion("Are you vegetarian?", listOf("Yes", "No")),
        SurveyQuestion("Do you prefer organic food?", listOf("Yes", "No")),
        SurveyQuestion("Do you consume dairy products?", listOf("Yes", "No")),
        SurveyQuestion("Do you eat fast food?", listOf("Yes", "No")),
        SurveyQuestion("Do you have any food allergies?", listOf("Yes", "No"))
    )
}