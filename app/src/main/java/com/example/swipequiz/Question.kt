package com.example.swipequiz

class Question(
    var text: String,
    var isQuestionTrue: Boolean
) {
    companion object {
        val QUESTIONS_TEXT = arrayOf(
            "The sky is blue.",
            "You are a student at HvA",
            "Water is wet",
            "Grass is green",
            "You live in Hoofddorp"
        )
        val QUESTION_TRUE = arrayOf(
            true,
            true,
            true,
            true,
            true
        )
    }
}