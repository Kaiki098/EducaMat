package br.com.kbat.educamat.presentation.screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import br.com.kbat.educamat.R
import br.com.kbat.educamat.data.preferences.UserPreferences
import org.koin.compose.koinInject


@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val userPreferences: UserPreferences = koinInject()
    val userName by userPreferences.userName.collectAsState(initial = "")
    val schoolYear by userPreferences.schoolYear.collectAsState(initial = "")
    
    Box {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "SettingsScreen")
            Text(text = "User name: $userName")
            Text(text = "School year: $schoolYear")
        }
    }
}