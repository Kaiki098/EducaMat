package br.com.kbat.educamat.presentation.screen.singUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.kbat.educamat.R
import br.com.kbat.educamat.data.preferences.UserPreferences
import kotlinx.coroutines.launch
import org.koin.compose.koinInject


@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    val userPreferences: UserPreferences = koinInject()
    val scope = rememberCoroutineScope()
    var userName by remember { mutableStateOf("") }
    var schoolYear by remember { mutableStateOf("") }
    //TODO personalize screen
    Box {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "SignUpScreen")
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Nome") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = schoolYear,
                onValueChange = { schoolYear = it },
                label = { Text("Ano Escolar") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                scope.launch {
                    userPreferences.setIsUserLoggedIn(true)
                    userPreferences.saveUserName(userName)
                    userPreferences.saveSchoolYear(schoolYear)
                }
            }) {
                Text("Salvar")
            }
        }
    }
}