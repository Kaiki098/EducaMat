package br.com.kbat.educamat.presentation.screen.questions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
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
import br.com.kbat.educamat.presentation.theme.EducaMatTheme

@Composable
fun QuestionsScreen(modifier: Modifier = Modifier, onStartCLick: () -> Unit) {
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

            List(4) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    onClick = { onStartCLick() },
                    shape = RoundedCornerShape(10),
                    colors = ButtonColors(
                        containerColor = Color.White,
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        disabledContentColor = Color.White,
                        disabledContainerColor = Color.White
                    ),
                    border = BorderStroke(10.dp, color = Color.Blue)
                ) {
                    Text(
                        text = "Começar",
                        fontSize = 32.sp,
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun QuestionsScreenPreview() {
    EducaMatTheme {
        QuestionsScreen(onStartCLick = {})
    }
}