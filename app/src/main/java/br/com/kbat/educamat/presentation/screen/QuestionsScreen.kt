package br.com.kbat.educamat.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.kbat.educamat.R
import br.com.kbat.educamat.data.preferences.UserPreferences
import br.com.kbat.educamat.presentation.components.OperationButton
import br.com.kbat.educamat.presentation.components.OutlinedText
import br.com.kbat.educamat.presentation.theme.Blue800
import br.com.kbat.educamat.presentation.theme.BlueColorScheme
import br.com.kbat.educamat.presentation.theme.EducaMatTheme
import br.com.kbat.educamat.presentation.theme.Pink
import br.com.kbat.educamat.presentation.theme.Red
import br.com.kbat.educamat.presentation.theme.Yellow
import br.com.kbat.educamat.presentation.viewmodel.QuestionViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun QuestionsScreen(
    modifier: Modifier = Modifier,
    onNavigateToQuestionClick: () -> Unit, // FIXME noma ruim?
    questionViewModel: QuestionViewModel = koinViewModel(),
    userPreferences: UserPreferences = koinInject()
) {
    val numberOfQuestions = userPreferences.questionsPerRound.collectAsState(10).value ?: 10
    val maxValue = userPreferences.maxValue.collectAsState(100).value ?: 100

    val onStartClick: (String) -> Unit = { operation ->
        questionViewModel.loadQuestions(operation, numberOfQuestions, maxValue)
        if (questionViewModel.questions.value.isNotEmpty()) onNavigateToQuestionClick()
    }

    Questions(modifier = modifier, onStartClick = onStartClick)
}


@Composable
private fun Questions(
    modifier: Modifier = Modifier,
    onStartClick: (String) -> Unit
) {
    Box(
        modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp, end = 26.dp, start = 26.dp, top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedText("Perguntas")

            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Adição",
                borderColor = Red,
                onStartClick = {
                    onStartClick("addition")
                }
            )

            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Subtração",
                borderColor = Yellow,
                onStartClick = { onStartClick("subtraction") }
            )

            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Multiplicação",
                borderColor = Blue800,
                onStartClick = {
                    onStartClick("multiplication")
                }
            )

            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Divisão",
                borderColor = Pink,
                onStartClick = {
                    onStartClick("division")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuestionsScreenPreview() {
    EducaMatTheme(
        colorScheme = BlueColorScheme
    ) {
        Questions(onStartClick = {})
    }
}