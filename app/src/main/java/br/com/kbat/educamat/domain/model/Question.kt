package br.com.kbat.educamat.domain.model

data class Question(
    val id: Int,
    val expression: String,
    val options: List<String>,
    val correctAnswer: String
)
