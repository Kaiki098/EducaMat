package br.com.kbat.educamat.presentation.screen.progress

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.R
import br.com.kbat.educamat.presentation.theme.EducaMatTheme
import br.com.kbat.educamat.presentation.theme.Orange

@Composable
fun Chart(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(150.dp)
            .width(350.dp)
            .background(color = Orange, shape = RoundedCornerShape(20))
    )
}

@Composable
fun QuestionItem(
    modifier: Modifier = Modifier,
    question: QuestionUI
) {
    var showQuestion by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier.clickable {
            showQuestion = !showQuestion
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = question.icon),
                    contentDescription = question.iconDescription
                )
                Text(text = "Q${question.questionNumber}", color = Color.Red, fontSize = 24.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = question.questionPreview,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 24.sp
                )
            }

            Box {
                Text(text = "Tempo gasto: ${question.questionTime}", fontSize = 20.sp)
            }

        }
        AnimatedVisibility(visible = showQuestion) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(text = question.questionDescription, fontSize = 20.sp)
                Text(text = "Sua resposta: " + question.answear, fontSize = 20.sp)
                Text(text = "Resposta correta: " + question.correctAnswear, fontSize = 20.sp)
            }
        }
    }
}

data class QuestionUI(
    val icon: Int,
    val iconDescription: String,
    val questionNumber: Int,
    val questionPreview: String,
    val questionTime: Int,
    val questionDescription: String,
    val answear: String,
    val correctAnswear: String
)

@Preview
@Composable
private fun ChartPreview() {
    EducaMatTheme {
        Chart()
    }
}

@Preview(showBackground = true)
@Composable
private fun QuestionItemPreview() {
    EducaMatTheme {
        QuestionItem(
            question = QuestionUI(
                icon = R.drawable.correct_icon,
                iconDescription = "Ícone de questão correta",
                questionNumber = 1,
                questionPreview = "4+2 é...",
                questionTime = 20,
                questionDescription = "Qual é a soma de 4 + 2?",
                answear = "8",
                correctAnswear = "6"
            )
        )
    }
}