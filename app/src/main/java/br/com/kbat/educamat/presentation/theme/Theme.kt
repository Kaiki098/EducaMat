package br.com.kbat.educamat.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val OrangeColorScheme = customColorScheme(
    primary = Orange,
    onPrimary = Color.Black,
    background = LightOrange,
    onBackground = Color.Black,
    onSurface = Gray
)

val BlueColorScheme = customColorScheme(
    primary = Blue,
    onPrimary = Color.Black,
    background = LightBlue,
    onBackground = Color.Black,
    onSurface = Gray
)

val themes = mapOf(
    "orange" to OrangeColorScheme,
    "blue" to BlueColorScheme
)


@Composable
fun EducaMatTheme(
    colorScheme: ColorScheme = OrangeColorScheme,
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

