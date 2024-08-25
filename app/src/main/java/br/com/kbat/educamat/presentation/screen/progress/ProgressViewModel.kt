package br.com.kbat.educamat.presentation.screen.progress

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.kbat.educamat.data.repositories.QuestionRepository
import br.com.kbat.educamat.domain.model.AnsweredQuestion
import br.com.kbat.educamat.domain.model.toQuestionUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.EnumMap

data class ProgressUIState(
    val questions: List<QuestionUI> = emptyList(),
    val dailyStatistics: EnumMap<DayOfWeek, Dp> = EnumMap(DayOfWeek::class.java),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

class ProgressViewModel(
    questionRepository: QuestionRepository
) : ViewModel() { // TODO Revisar implementação

    private val _uiState = MutableStateFlow(ProgressUIState())
    val uiState: StateFlow<ProgressUIState> = _uiState.asStateFlow()

    private val answeredQuestions: StateFlow<List<AnsweredQuestion>> =
        questionRepository.answeredQuestions //FIXME DRY

    init {
        viewModelScope.launch {
            answeredQuestions.collect { answeredQuestions -> // FIXME Estou fazendo algo errado?
                val questions = answeredQuestions.map { it.toQuestionUI() }
                val dailyStatistics = getDailyStatistics()
                _uiState.update {
                    it.copy(
                        questions = questions,
                        dailyStatistics = dailyStatistics,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun getDailyStatistics(): EnumMap<DayOfWeek, Dp> {
        val last7Days = LocalDate.now().minusDays(7)
        val recentQuestions = answeredQuestions.value
            .filter { it.day >= last7Days }
            .groupBy { it.day.dayOfWeek }
            .mapValues { (_, question) -> question.sumOf { it.time } }

        var maxValue = 100
        if (recentQuestions.isNotEmpty()) {
            maxValue = recentQuestions.maxOf { it.value }
        }
        val scaleFactor = if (maxValue > 100) 100.0 / maxValue else 1.0

        return recentQuestions
            .mapValues { (_, dayStatistic) -> (dayStatistic * scaleFactor).dp }
            .toMap(EnumMap(DayOfWeek::class.java))
    }

}