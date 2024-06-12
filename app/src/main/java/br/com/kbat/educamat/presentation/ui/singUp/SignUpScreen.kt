package br.com.kbat.educamat.presentation.ui.singUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.kbat.educamat.presentation.utils.getContrastColor
import br.com.kbat.educamat.presentation.utils.getRandomColor



@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    val backgroundColor = getRandomColor()
    /* Essa funcinalidade das cores aleatórias,
podem ser apagadas, era só teste
 */
    Column(
        modifier = modifier.background(Color(backgroundColor)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "SignUpScreen", color = Color(getContrastColor(backgroundColor)))
    }
}