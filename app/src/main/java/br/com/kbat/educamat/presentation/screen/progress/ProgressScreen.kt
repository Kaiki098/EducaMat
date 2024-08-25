package br.com.kbat.educamat.presentation.screen.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.R
import br.com.kbat.educamat.presentation.theme.EducaMatTheme
import br.com.kbat.educamat.presentation.utils.ColorUtil
import org.koin.androidx.compose.koinViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.EnumMap
import kotlin.random.Random

@Composable
fun ProgressScreen(
    modifier: Modifier = Modifier,
    viewModel: ProgressViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        LoadingProgress(modifier)
    } else {
        Progress(
            modifier = modifier,
            questions = uiState.questions,
            dailyStatistics = uiState.dailyStatistics
        )
    }
}

@Composable
fun LoadingProgress(modifier: Modifier = Modifier) {
    Column(
        modifier.background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator() // Se começar a demorar de mais, crie um skeleton
    }
}

@Composable
fun Progress(
    modifier: Modifier = Modifier,
    questions: List<QuestionUI>,
    dailyStatistics: EnumMap<DayOfWeek, Dp>
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 24.dp),
            text = "Progresso diário",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 32.sp
        )
        WeekChart(
            dailyStatistics = dailyStatistics,
            modifier = Modifier.padding(20.dp)
        )
        Text(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
            text = "Questões",
            fontSize = 32.sp
        )
        if (questions.isEmpty()) {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Não há questões respondidas",
                fontSize = 18.sp
            )
        }
        LazyColumn {
            items(questions) { question ->
                QuestionItem(question = question)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingPreview() {
    EducaMatTheme {
        LoadingProgress()
    }
}

@Preview(showBackground = true)
@Composable
private fun ProgressScreenPreview() {
    val questions = List(7) {
        QuestionUI(
            icon = R.drawable.wrong_icon,
            iconDescription = "Ícone de questão incorreta",
            number = it + 1,
            color = ColorUtil.getRandomColor(),
            preview = "2 + 2 é...",
            questionTime = Random.nextInt(5, 100),
            description = "Quanto é 2 + 2?",
            userAnswear = "4",
            correctAnswear = "4",
            day = LocalDate.now()
        )
    }
    val dailyStatistics = questions
        .groupBy { it.day.dayOfWeek }
        .mapValues { (_, questions) -> questions.sumOf { it.questionTime }.dp }
        .toMap(EnumMap(DayOfWeek::class.java))

    EducaMatTheme {
        Progress(
            modifier = Modifier.fillMaxSize(),
            questions = questions,
            dailyStatistics = dailyStatistics
        )
    }
}