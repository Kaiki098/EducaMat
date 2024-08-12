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
    private val questionRepository: QuestionRepository
) : ViewModel() {

    val answeredQuestions: StateFlow<List<AnsweredQuestion>> = questionRepository.answeredQuestions

    fun getDailyStatistics(): EnumMap<DayOfWeek, Dp> {
        val last7Days = LocalDate.now().minusDays(7)
        return answeredQuestions.value
            .filter { it.day >= last7Days }
            .groupBy { it.day.dayOfWeek }
            .mapValues { (_, questions) -> questions.sumOf { it.time }.dp }
            .toMap(EnumMap(DayOfWeek::class.java))
    }

}