package br.com.kbat.educamat.domain.generator

import br.com.kbat.educamat.domain.model.Question
import kotlin.random.Random

object QuestionGenerator {

    fun generate(operation: String, numberOfQuestions: Int, maxValue: Int): List<Question> {
        return List(numberOfQuestions) { i ->
            val (n1, n2) = generateOperands(maxValue, operation)
            val correctAnswer = calculateCorrectAnswer(n1, n2, operation)
            val options = generateOptions(correctAnswer, maxValue)
            val expression = formatExpression(n1, n2, operation)

            Question(
                id = i,
                expression = expression,
                options = options,
                correctAnswer = correctAnswer.toString()
            )
        }
    }

    private fun generateOperands(maxValue: Int, operation: String): Pair<Int, Int> {
        val n1 = Random.nextInt(maxValue)
        var n2 = Random.nextInt(maxValue)
        if (operation == "division") {
            while (n2 == 0) {
                n2 = Random.nextInt(maxValue)
            }
        }
        return Pair(n1, n2)
    }

    private fun calculateCorrectAnswer(n1: Int, n2: Int, operation: String): Number {
        return when (operation) {
            "addition" -> n1 + n2
            "subtraction" -> n1 - n2
            "multiplication" -> n1 * n2
            "division" -> n1 / n2.toFloat()
            else -> 0
        }
    }

    private fun generateOptions(correctAnswer: Number, maxValue: Int): List<String> {
        val options = mutableSetOf(correctAnswer.toString())
        while (options.size < 4) {
            options.add(Random.nextInt(maxValue).toString())
        }
        return options.toList().shuffled()
    }

    private fun formatExpression(n1: Int, n2: Int, operation: String): String {
        val operator = when (operation) {
            "addition" -> "+"
            "subtraction" -> "-"
            "multiplication" -> "x"
            "division" -> "/"
            else -> ""
        }
        return "$n1 $operator $n2"
    }
}
