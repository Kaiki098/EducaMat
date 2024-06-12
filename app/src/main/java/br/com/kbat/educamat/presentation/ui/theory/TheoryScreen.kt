package br.com.kbat.educamat.presentation.ui.theory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.kbat.educamat.presentation.utils.getContrastColor
import br.com.kbat.educamat.presentation.utils.getRandomColor

@Composable
fun TheoryScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    val backgroundColor = getRandomColor()
    /* Essa funcinalidade das cores aleatórias,
podem ser apagadas, era só teste
*/
    Column (
        modifier.background(Color(backgroundColor)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "TheoryScreen", color = Color(getContrastColor(backgroundColor)))
        Button(onClick = { onBackClick() }) {
            Text(text = "Voltar")
        }
    }
}