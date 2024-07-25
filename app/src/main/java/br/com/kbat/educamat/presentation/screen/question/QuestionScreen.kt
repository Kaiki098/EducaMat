package br.com.kbat.educamat.presentation.screen.question

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.R
import br.com.kbat.educamat.presentation.screen.questions.QuestionChoice
import br.com.kbat.educamat.presentation.theme.EducaMatTheme
import br.com.kbat.educamat.presentation.theme.Orange

@Composable
fun QuestionScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier.padding(horizontal = 30.dp, vertical = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .border(width = 10.dp, color = Orange, shape = RoundedCornerShape(20))
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Pergunta", fontSize = 24.sp)
            }

            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .padding(vertical = 60.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                List(4) {
                    QuestionChoice(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onClick = { /*TODO*/ },
                        text = "Resposta"
                    )
                }
            }

        }

    }
}

@Preview
@Composable
private fun QuestionScreenPreview() {
    EducaMatTheme {
        QuestionScreen(modifier = Modifier.fillMaxSize(), onBackClick = {})
    }
}