package br.com.kbat.educamat.presentation.ui.theories

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
fun TheoriesScreen(modifier: Modifier = Modifier) {
    val backgroundColor = getRandomColor()
    Column (
        modifier.background(Color(backgroundColor)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "TheoriesScreen", color = Color(getContrastColor(backgroundColor)))
    }
}