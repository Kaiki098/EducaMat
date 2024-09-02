package br.com.kbat.educamat.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import br.com.kbat.educamat.presentation.components.BottomBar


val OrangeColorScheme = customColorScheme(
    primary = Orange700,
    onPrimary = White,
    background = White,
    onBackground = Color.Black,
    surface = Orange700,
    onSurface = Orange800, // Chart and question preview
//    error = Red,
//    scrim = Orange100,
//    tertiary = Orange700,
//    onTertiaryContainer = Orange900,
//    inverseOnSurface = Orange100,
//    onErrorContainer = Orange700,
    onSurfaceVariant = Orange800, // Bottom bar text and icon disabled
//    primaryContainer = Orange100,
    onSecondaryContainer = White, // Bottom bar icon
//    onPrimaryContainer = Orange900,
//    secondaryContainer = Orange100,
//    errorContainer = Orange700,
//    inversePrimary = Orange900,
//    inverseSurface = Orange100,
//    outlineVariant = Orange700,
    surfaceVariant = Orange700, // Disabled Switch container
//    onError = Orange100,
    outline = Orange800, // Disabled Switch border and ball
//    secondary = Orange900,
//    onTertiary = Orange100,
//    onSecondary = Orange700,
//    surfaceTint = Orange900
)

val BlueColorScheme = customColorScheme(
    primary = Blue700,
    onPrimary = White,
    background = White,
    onBackground = Color.Black,
    surface = Blue700,
    onSurface = Blue800,
    surfaceVariant = Blue700,
    outline = Blue800,
    onSurfaceVariant = Blue800,
    onSecondaryContainer = White,
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

@Preview
@Composable
private fun SettingsPreview() {
    EducaMatTheme(
        colorScheme = BlueColorScheme
    ) {
        BottomBar(navController = rememberNavController())
    }
}


@Preview
@Composable
private fun SettingsOrangePreview() {
    EducaMatTheme(
        colorScheme = OrangeColorScheme
    ) {
        BottomBar(navController = rememberNavController())
    }
}


