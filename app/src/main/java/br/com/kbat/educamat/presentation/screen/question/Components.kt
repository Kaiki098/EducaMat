package br.com.kbat.educamat.presentation.screen.question

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import br.com.kbat.educamat.presentation.utils.ColorUtil

@Composable
fun QuestionItem(
    modifier: Modifier = Modifier,
    question: QuestionUI
) {
    var showQuestion by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { showQuestion = !showQuestion }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(end = 8.dp),
                    painter = painterResource(id = question.icon),
                    contentDescription = question.iconDescription,
                    tint = if (question.icon == R.drawable.wrong_icon) Color.Red else Color.Green
                )
                Text(text = "Q${question.number}", color = Color(question.color), fontSize = 24.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = question.preview,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp
                )
            }
            Box {
                Text(text = "Tempo: ${question.questionTime}", fontSize = 20.sp)
            }
        }
        AnimatedVisibility(visible = showQuestion) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(text = question.description, fontSize = 20.sp)
                Text(text = "Sua resposta: " + question.userAnswear, fontSize = 20.sp)
                Text(text = "Resposta correta: " + question.correctAnswear, fontSize = 20.sp)
            }
        }
    }
}

data class QuestionUI(
    val icon: Int,
    val iconDescription: String,
    val number: Int,
    val color: Int,
    val preview: String,
    val questionTime: Int,
    val description: String,
    val userAnswear: String,
    val correctAnswear: String
)

@Preview(showBackground = true)
@Composable
private fun QuestionItemPreview() {
    EducaMatTheme {
        QuestionItem(
            Modifier,
            question = QuestionUI(
                icon = R.drawable.wrong_icon,
                iconDescription = "Ícone de questão correta",
                number = 1,
                color = ColorUtil.getRandomColor(),
                preview = "4+2 é...",
                questionTime = 20,
                description = "Qual é a soma de 4 + 2?",
                userAnswear = "8",
                correctAnswear = "6"
            )
        )
    }
}