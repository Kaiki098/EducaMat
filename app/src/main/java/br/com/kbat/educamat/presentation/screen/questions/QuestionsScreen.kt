package br.com.kbat.educamat.presentation.screen.questions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.R
import br.com.kbat.educamat.presentation.screen.components.OperationButton
import br.com.kbat.educamat.presentation.theme.Blue
import br.com.kbat.educamat.presentation.theme.EducaMatTheme
import br.com.kbat.educamat.presentation.theme.Pink
import br.com.kbat.educamat.presentation.theme.Red
import br.com.kbat.educamat.presentation.theme.Yellow
import br.com.kbat.educamat.presentation.viewmodel.QuestionViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuestionsScreen(
    modifier: Modifier = Modifier, onStartClick: () -> Unit,
    questionViewModel: QuestionViewModel = koinViewModel()
) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 40.dp, end = 26.dp, start = 26.dp, top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Perguntas", fontSize = 32.sp)

            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Adição",
                borderColor = Red,
                onStartClick = {
                    questionViewModel.generateQuestions("addition", 10, 101)
                    if (questionViewModel.questions.value.isNotEmpty()) onStartClick()
                }
            )

            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Subtração",
                borderColor = Yellow,
                onStartClick = {
                    questionViewModel.generateQuestions("subtraction", 10, 101)
                    if (questionViewModel.questions.value.isNotEmpty()) onStartClick()
                }
            )

            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Multiplicação",
                borderColor = Blue,
                onStartClick = {
                    questionViewModel.generateQuestions("multiplication", 10, 101)
                    if (questionViewModel.questions.value.isNotEmpty()) onStartClick()
                }
            )

            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Divisão",
                borderColor = Pink,
                onStartClick = {
                    questionViewModel.generateQuestions("division", 10, 101)
                    if (questionViewModel.questions.value.isNotEmpty()) onStartClick()
                }
            )
        }
    }
}

@Preview
@Composable
private fun QuestionsScreenPreview() {
    EducaMatTheme {
        QuestionsScreen(onStartClick = {})
    }
}