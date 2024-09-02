package br.com.kbat.educamat.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.presentation.theme.EducaMatTheme

@Composable
fun OutlinedText(text: String, modifier: Modifier = Modifier) {
    Box {
        Text(
            modifier = modifier,
            text = text,
            color = Color.White,
            fontSize = 32.sp,
            style = LocalTextStyle.current.merge(
                drawStyle = Stroke(
                    miter = 10f,
                    width = 10f,
                    join = StrokeJoin.Round
                )
            )
        )
        Text(
            modifier = modifier,
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 32.sp,
        )
    }
}

@Preview
@Composable
private fun OutlinedTextPreview() {
    EducaMatTheme {
        OutlinedText("Olá mundo")
    }
}