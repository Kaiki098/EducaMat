package br.com.kbat.educamat.presentation.screen.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.R
import br.com.kbat.educamat.presentation.theme.EducaMatTheme

@Composable
fun ProgressScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 24.dp),
            text = "Progresso diário",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 32.sp
        )
        Chart(modifier = Modifier.padding(20.dp))
        Text(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
            text = "Questões",
            fontSize = 32.sp
        )
        val questions = List(10) {
            QuestionUI(
                icon = R.drawable.correct_icon,
                iconDescription = "Ícone de questão correta",
                questionNumber = 1,
                questionPreview = "4+2 é...",
                questionTime = 20,
                questionDescription = "Qual é a soma de 4 + 2?",
                answear = "8",
                correctAnswear = "6"
            )
        }

        LazyColumn {
            items(questions) { question ->
                QuestionItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    question = question
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProgressScreenPreview() {
    EducaMatTheme {
        ProgressScreen()
    }
}

/* Essa funcinalidade das cores aleatórias,
podem ser apagadas, era só teste
 */