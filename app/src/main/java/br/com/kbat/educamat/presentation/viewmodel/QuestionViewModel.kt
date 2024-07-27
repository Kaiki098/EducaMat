package br.com.kbat.educamat.presentation.viewmodel

import androidx.lifecycle.ViewModel
import br.com.kbat.educamat.data.repositories.QuestionRepository
import br.com.kbat.educamat.domain.model.Question
import kotlinx.coroutines.flow.StateFlow

class QuestionViewModel(
    private val questionRepository: QuestionRepository
) : ViewModel() {

    val questions: StateFlow<List<Question>> = questionRepository.questions

    fun generateQuestions(operation: String, numberOfQuestions: Int, maxValue: Int) {
        questionRepository.generateQuestions(operation, numberOfQuestions, maxValue)
    }

}