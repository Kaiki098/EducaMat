package br.com.kbat.educamat.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.R
import br.com.kbat.educamat.presentation.theme.EducaMatTheme
import br.com.kbat.educamat.presentation.theme.OrangeColorScheme
import br.com.kbat.educamat.presentation.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit,
    userViewModel: UserViewModel = koinViewModel()
) {
    val onSaveClick: (String, String) -> Unit = { userName, schoolYear ->
        userViewModel.setIsUserLoggedIn(true)
        userViewModel.saveUserName(userName)
        userViewModel.saveSchoolYear(schoolYear)
        userViewModel.saveQuestionsPerRound(10)
        userViewModel.saveMaxValue(100)
        userViewModel.setTimer(true)
        onNavigateToHome()
    }
    SignUp(
        modifier = modifier,
        onSaveClick = onSaveClick
    )
}

@Composable
fun SignUp(
    modifier: Modifier = Modifier,
    onSaveClick: (String, String) -> Unit
) {
    var userName by remember { mutableStateOf("") }
    var schoolYear by remember { mutableStateOf("") }
    Box {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Dados", color = MaterialTheme.colorScheme.primary, fontSize = 52.sp)

            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(width = 10.dp, MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(10)
                    )
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Nome:",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 32.sp
                    )
                    TextField(
                        value = userName,
                        textStyle = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                        onValueChange = { userName = it },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            disabledTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Ano Escolar:",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 32.sp
                    )
                    TextField(
                        value = schoolYear,
                        textStyle = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                        onValueChange = { schoolYear = it },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            disabledTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(0.7f),
                        onClick = {
                            onSaveClick(userName, schoolYear)
                        }
                    ) {
                        Text(
                            "Salvar",
                            color = Color.White,
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    EducaMatTheme(OrangeColorScheme) {
        SignUp(
            modifier = Modifier.fillMaxSize(),
            onSaveClick = { _, _ -> })
    }
}