package br.com.kbat.educamat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import br.com.kbat.educamat.R
import br.com.kbat.educamat.presentation.navigation.ProgressRoute
import br.com.kbat.educamat.presentation.navigation.QuestionsRoute
import br.com.kbat.educamat.presentation.navigation.SettingsRoute
import br.com.kbat.educamat.presentation.navigation.TheoriesRoute
import br.com.kbat.educamat.presentation.theme.BlueColorScheme
import br.com.kbat.educamat.presentation.theme.EducaMatTheme


sealed class HomeNavItem(
    val label: String,
    val icon: Int,
    val route: String
) {
    data object Progress : HomeNavItem(
        label = "Progresso",
        icon = R.drawable.progress,
        route = ProgressRoute
    )

    data object Questions : HomeNavItem(
        label = "Perguntas",
        icon = R.drawable.minus,
        route = QuestionsRoute
    )

    data object Theories : HomeNavItem(
        label = "Teorias",
        icon = R.drawable.multiply,
        route = TheoriesRoute
    )

    data object Settings : HomeNavItem(
        label = "Ajustes",
        icon = R.drawable.divide,
        route = SettingsRoute
    )
}


@Composable
fun BottomBar(navController: NavHostController) {
    val items = remember {
        listOf(
            HomeNavItem.Progress,
            HomeNavItem.Questions,
            HomeNavItem.Theories,
            HomeNavItem.Settings
        )
    }

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination?.route

    BottomAppBar(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .clip(
                shape = RoundedCornerShape(
                    topStartPercent = 30,
                    topEndPercent = 30
                )
            ),
        actions = {
            items.forEach { item ->
                NavigationBarItem(
                    selected = currentDestination == item.route,
                    onClick = {
                        navController.navigate(item.route, navOptions = navOptions {
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId)
                        })
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.height(20.dp),
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                            fontSize = 12.sp
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        indicatorColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f)
                    )
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.primary
    )
}

@Preview(showSystemUi = true)
@Composable
private fun BottomBarPreview() {
    val navController: NavHostController = rememberNavController()
    EducaMatTheme(
        BlueColorScheme
    ) {
        BottomBar(navController = navController)
    }
}