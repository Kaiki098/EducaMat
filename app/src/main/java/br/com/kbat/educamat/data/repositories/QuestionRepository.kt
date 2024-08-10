package br.com.kbat.educamat.data.repositories

import br.com.kbat.educamat.data.db.dao.AnsweredQuestionDAO
import br.com.kbat.educamat.data.db.entities.AnsweredQuestionEntity
import br.com.kbat.educamat.domain.generator.QuestionGenerator
import br.com.kbat.educamat.domain.model.AnsweredQuestion
import br.com.kbat.educamat.domain.model.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun createQuestions( // TODO TALVEZ TROCAR PARA CREATE APENAS?
        operation: String,
        numberOfQuestions: Int,
        maxValue: Int //Não é inclusivo
    ) {
        _questions.value = QuestionGenerator.generate(operation, numberOfQuestions, maxValue)
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
    expression = this.expression,
    day = this.day
)

fun AnsweredQuestionEntity.toAnsweredQuestion() = AnsweredQuestion(
    id = this.id,
    answerGiven = this.answerGiven,
    correctAnswer = this.correctAnswer,
    options = this.options,
    expression = this.expression,
    day = this.day
)