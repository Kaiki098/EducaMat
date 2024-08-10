package br.com.kbat.educamat.presentation.screen.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.R
import br.com.kbat.educamat.presentation.theme.EducaMatTheme
import br.com.kbat.educamat.presentation.utils.ColorUtil
import org.koin.androidx.compose.koinViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.EnumMap

@Composable
fun ProgressScreen(
    modifier: Modifier = Modifier,
    viewModel: ProgressViewModel = koinViewModel()
) {
    val answeredQuestions by viewModel.answeredQuestions.collectAsState()
    val questions = answeredQuestions.map { question -> // TODO Arrumar um UIState pra cá
        QuestionUI(
            icon = if (question.correctAnswer == question.answerGiven) R.drawable.correct_icon else R.drawable.wrong_icon,
            iconDescription = if (question.correctAnswer == question.answerGiven) "Ícone de questão correta" else "Ícone de questão incorreta",
            number = question.id,
            color = ColorUtil.getRandomColor(),
            preview = "${question.expression} é...",
            questionTime = 20,
            description = "Quanto é ${question.expression}?",
            userAnswear = question.answerGiven,
            correctAnswear = question.correctAnswer,
            day = question.day
        )
    }
    Progress(
        modifier = modifier,
        questions = questions
    )
}

@Composable
fun Progress(
    modifier: Modifier = Modifier,
    questions: List<QuestionUI>
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
        val last7Days = LocalDate.now().minusDays(7)
        val barHeight = questions
            .filter { it.day >= last7Days }
            .groupBy { it.day.dayOfWeek }
            .mapValues { (_, questions) -> questions.sumOf { it.questionTime }.dp }
            .toMap(EnumMap(DayOfWeek::class.java)) //FIXME talvez usar um remember / state
        WeekChart(
            barHeight = barHeight,
            modifier = Modifier.padding(20.dp)
        )
        Text(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
            text = "Questões",
            fontSize = 32.sp
        )


        LazyColumn {
            items(questions) { question ->
                QuestionItem(question = question)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProgressScreenPreview() {
    EducaMatTheme {
        Progress(
            modifier = Modifier.fillMaxSize(),
            List(7) {
                QuestionUI(
                    icon = R.drawable.wrong_icon,
                    iconDescription = "Ícone de questão incorreta",
                    number = it + 1,
                    color = ColorUtil.getRandomColor(),
                    preview = "2 + 2 é...",
                    questionTime = 20,
                    description = "Quanto é 2 + 2?",
                    userAnswear = "4",
                    correctAnswear = "4",
                    day = LocalDate.now()
                )
            }
        )
    }
}

/* Essa funcinalidade das cores aleatórias,
podem ser apagadas, era só teste
 */