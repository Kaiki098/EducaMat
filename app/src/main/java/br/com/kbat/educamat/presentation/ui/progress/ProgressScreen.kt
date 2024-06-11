package br.com.kbat.educamat.presentation.ui.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.kbat.educamat.presentation.utils.getContrastColor
import br.com.kbat.educamat.presentation.utils.getRandomColor

/* Essa funcinalidade das cores aleatórias,
podem ser apagadas, era só teste
 */

@Composable
fun ProgressScreen(modifier: Modifier = Modifier) {
    val backgroundColor = getRandomColor()
    Column(
        modifier = modifier
            .background(Color(backgroundColor)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "ProgressScreen", color = Color(getContrastColor(backgroundColor)))
    }
}

@Preview
@Composable
private fun ProgressScreenPreview() {
    ProgressScreen(Modifier.fillMaxSize())
}