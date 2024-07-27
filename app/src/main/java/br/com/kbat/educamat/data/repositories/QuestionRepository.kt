package br.com.kbat.educamat.data.repositories

import br.com.kbat.educamat.domain.model.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class QuestionRepository {

    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions


    fun generateQuestions(
        operation: String,
        numberOfQuestions: Int,
        maxValue: Int
    ) {
        //FIXME Sinto que isso possa ser um pouco mais separado em outra partes
        _questions.value = List(numberOfQuestions) { i ->
            val n1 = Random.nextInt(maxValue)
            var n2 = Random.nextInt(maxValue)
            val correctAnswear = when (operation) {
                "addition" -> n1 + n2
                "subtraction" -> n1 - n2
                "multiplication" -> n1 * n2
                "division" -> {
                    while (n2 == 0) {
                        n2 = Random.nextInt(maxValue)
                    }
                    n1 / n2
                }

                else -> 0
            }
            val options = mutableSetOf(correctAnswear.toString())
            while (options.size < 4) {
                options.add(Random.nextInt(maxValue).toString())
            }
            val arithmeticOperator = when (operation) {
                "addition" -> "+"
                "subtraction" -> "-"
                "multiplication" -> "x"
                "division" -> "/"
                else -> ""
            }
            Question(
                id = i,
                expression = "$n1 $arithmeticOperator $n2",
                options = options.toList().shuffled(),
                correctAnswer = "$correctAnswear"
            )
        }
    }

}