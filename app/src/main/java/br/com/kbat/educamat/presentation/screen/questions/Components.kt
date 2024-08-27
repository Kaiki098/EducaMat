package br.com.kbat.educamat.presentation.screen.questions

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.presentation.theme.EducaMatTheme

@Composable
fun QuestionChoice(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors()
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(20),
        onClick = { onClick() },
        colors = colors
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
        )
    }
}

@Preview
@Composable
private fun QuestionChoicePreview() {
    EducaMatTheme {
        QuestionChoice(onClick = {}, text = "456")
    }
}