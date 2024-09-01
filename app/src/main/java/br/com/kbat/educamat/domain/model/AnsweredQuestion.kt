package br.com.kbat.educamat.domain.model

import br.com.kbat.educamat.R
import br.com.kbat.educamat.presentation.components.QuestionUI
import br.com.kbat.educamat.presentation.utils.ColorUtil
import java.time.LocalDate

data class AnsweredQuestion(
    val id: Int = 0,
    val expression: String,
    val options: List<String>,
    val correctAnswer: String,
    val answerGiven: String,
    val day: LocalDate,
    val time: Int
)

fun AnsweredQuestion.toQuestionUI(): QuestionUI {
    return QuestionUI(
        icon = if (correctAnswer == answerGiven) R.drawable.correct_icon else R.drawable.wrong_icon,
        iconDescription = if (correctAnswer == answerGiven) "Ícone de questão correta" else "Ícone de questão incorreta",
        number = id,
        color = ColorUtil.getRandomColor(),
        preview = "$expression é...",
        questionTime = time,
        description = "Quanto é $expression?",
        userAnswear = answerGiven,
        correctAnswear = correctAnswer,
        day = day
    )
}