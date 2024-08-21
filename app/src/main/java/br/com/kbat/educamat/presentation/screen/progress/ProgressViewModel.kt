package br.com.kbat.educamat.presentation.screen.progress

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import br.com.kbat.educamat.data.repositories.QuestionRepository
import br.com.kbat.educamat.domain.model.AnsweredQuestion
import kotlinx.coroutines.flow.StateFlow
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.EnumMap

class ProgressViewModel(
    questionRepository: QuestionRepository
) : ViewModel() {

    val answeredQuestions: StateFlow<List<AnsweredQuestion>> = questionRepository.answeredQuestions

    fun getDailyStatistics(): EnumMap<DayOfWeek, Dp> {
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