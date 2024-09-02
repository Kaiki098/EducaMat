package br.com.kbat.educamat.presentation.viewmodel

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.kbat.educamat.data.repositories.QuestionRepository
import br.com.kbat.educamat.domain.model.AnsweredQuestion
import br.com.kbat.educamat.domain.model.toQuestionUI
import br.com.kbat.educamat.presentation.components.QuestionUI
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
    val dailyStatisticsScaled: EnumMap<DayOfWeek, Dp> = EnumMap(DayOfWeek::class.java),
    val dailyStatisticsUnscaled: EnumMap<DayOfWeek, Int> = EnumMap(DayOfWeek::class.java),
    val isLoading: Boolean = true
)

class ProgressViewModel(
    private val questionRepository: QuestionRepository
) : ViewModel() { // TODO Revisar implementação

    private val _uiState = MutableStateFlow(ProgressUIState())
    val uiState: StateFlow<ProgressUIState> = _uiState.asStateFlow()

    private val answeredQuestions: StateFlow<List<AnsweredQuestion>> =
        questionRepository.answeredQuestions //FIXME DRY

    init {
        viewModelScope.launch {
            answeredQuestions.collect { answeredQuestions -> // FIXME Estou fazendo algo errado?
                val questions = answeredQuestions.map { it.toQuestionUI() }
                val dailyStatisticsScaled = getDailyStatisticsScaled()
                val dailyStatisticsUnscaled = getDailyStatisticsUnscaled()
                _uiState.update {
                    it.copy(
                        questions = questions,
                        dailyStatisticsScaled = dailyStatisticsScaled,
                        dailyStatisticsUnscaled = dailyStatisticsUnscaled,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun getDailyStatisticsScaled(): EnumMap<DayOfWeek, Dp> {
        val dailyStatisticsUnscaled = getDailyStatisticsUnscaled()
        return scaleDailyStatistics(dailyStatisticsUnscaled)
    }

    private fun getDailyStatisticsUnscaled(): EnumMap<DayOfWeek, Int> {
        val last7Days = LocalDate.now().minusDays(7)

        return answeredQuestions.value
            .filter { it.day >= last7Days }
            .groupBy { it.day.dayOfWeek }
            .mapValues { (_, question) -> question.sumOf { it.time } }
            .toMap(EnumMap(DayOfWeek::class.java))
    }

    private fun scaleDailyStatistics(dailyStatistics: EnumMap<DayOfWeek, Int>): EnumMap<DayOfWeek, Dp> {
        var maxValue = 100
        if (dailyStatistics.isNotEmpty()) {
            maxValue = dailyStatistics.maxOf { it.value }
        }
        val scaleFactor = if (maxValue > 100) 100.0 / maxValue else 1.0

        return dailyStatistics
            .mapValues { (_, dayStatistic) -> (dayStatistic * scaleFactor).dp }
            .toMap(EnumMap(DayOfWeek::class.java))
    }

    fun deleteQuestion(id: Int) {
        viewModelScope.launch {
            questionRepository.deleteById(id = id)
        }
    }
}