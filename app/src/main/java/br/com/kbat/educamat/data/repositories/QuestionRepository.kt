package br.com.kbat.educamat.data.repositories

import br.com.kbat.educamat.data.db.dao.AnsweredQuestionDAO
import br.com.kbat.educamat.data.db.entities.AnsweredQuestionEntity
import br.com.kbat.educamat.domain.model.AnsweredQuestion
import br.com.kbat.educamat.domain.model.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class QuestionRepository(
    private val dao: AnsweredQuestionDAO
) {

    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions

    private val _answeredQuestions = MutableStateFlow<List<AnsweredQuestion>>(emptyList())
    val answeredQuestions: StateFlow<List<AnsweredQuestion>> = _answeredQuestions

    init {
        CoroutineScope(Dispatchers.IO).launch {
            dao.getAll()
                .map { entities -> entities.map { it.toAnsweredQuestion() } }
                .collect { _answeredQuestions.value = it }
        }
    }

    fun generateQuestions( // TODO TALVEZ CRIAR UM SERVICE PARA ISSO
        operation: String,
        numberOfQuestions: Int,
        maxValue: Int //Não é inclusivo
    ) {
        //FIXME Sinto que isso possa ser um pouco mais separado em outra partes
        _questions.value = List(numberOfQuestions) { i ->
            val n1 = Random.nextInt(maxValue)
            var n2 = Random.nextInt(maxValue)
            val correctAnswer = when (operation) {
                "addition" -> n1 + n2
                "subtraction" -> n1 - n2
                "multiplication" -> n1 * n2
                "division" -> {
                    while (n2 == 0) {
                        n2 = Random.nextInt(maxValue)
                    }
                    n1 / n2.toFloat()
                }

                else -> 0
            }
            val options = mutableSetOf(correctAnswer.toString())
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
                correctAnswer = "$correctAnswer"
            )
        }
    }

    suspend fun save(answeredQuestion: AnsweredQuestion) = withContext(Dispatchers.IO) {
        dao.save(answeredQuestion.toAnsweredQuestionEntity())
    }
}

fun AnsweredQuestion.toAnsweredQuestionEntity() = AnsweredQuestionEntity(
    id = this.id,
    answerGiven = this.answerGiven,
    correctAnswer = this.correctAnswer,
    options = this.options,
    expression = this.expression
)

fun AnsweredQuestionEntity.toAnsweredQuestion() = AnsweredQuestion(
    id = this.id,
    answerGiven = this.answerGiven,
    correctAnswer = this.correctAnswer,
    options = this.options,
    expression = this.expression
)