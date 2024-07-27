package br.com.kbat.educamat.presentation.screen.progress

import androidx.lifecycle.ViewModel
import br.com.kbat.educamat.data.repositories.QuestionRepository
import br.com.kbat.educamat.domain.model.AnsweredQuestion
import kotlinx.coroutines.flow.StateFlow

class ProgressViewModel(
    private val questionRepository: QuestionRepository
) : ViewModel() {

    val answeredQuestions: StateFlow<List<AnsweredQuestion>> =
        questionRepository.answeredQuestions // TODO Revisar pra ver se este é o melhor metodo

}