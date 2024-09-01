package br.com.kbat.educamat.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import br.com.kbat.educamat.data.preferences.UserPreferences
import org.koin.compose.koinInject


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
    userPreferences: UserPreferences = koinInject(),
    content: @Composable () -> Unit
) {
    val themeValue by userPreferences.theme.collectAsState(initial = "")
    val colorScheme = themes[themeValue] ?: OrangeColorScheme


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

