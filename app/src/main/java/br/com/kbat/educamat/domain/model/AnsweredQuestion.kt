package br.com.kbat.educamat.domain.model

import java.time.LocalDate

data class AnsweredQuestion(
    val id: Int = 0,
    val expression: String,
    val options: List<String>,
    val correctAnswer: String,
    val answerGiven: String,
    val day: LocalDate
)