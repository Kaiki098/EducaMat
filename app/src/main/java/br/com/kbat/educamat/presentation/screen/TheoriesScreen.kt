package br.com.kbat.educamat.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.kbat.educamat.R
import br.com.kbat.educamat.presentation.components.OperationButton
import br.com.kbat.educamat.presentation.components.OutlinedText
import br.com.kbat.educamat.presentation.theme.Blue800
import br.com.kbat.educamat.presentation.theme.EducaMatTheme
import br.com.kbat.educamat.presentation.theme.OrangeColorScheme
import br.com.kbat.educamat.presentation.theme.Pink
import br.com.kbat.educamat.presentation.theme.Red
import br.com.kbat.educamat.presentation.theme.Yellow

@Composable
fun TheoriesScreen(modifier: Modifier = Modifier, onStudyClick: () -> Unit) {
    Box(
        modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )


        Column(
            modifier = Modifier
                .padding(bottom = 40.dp, end = 26.dp, start = 26.dp, top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedText("Teorias")
            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Adição",
                borderColor = Red,
                onStartClick = { onStudyClick() }
            )

            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Subtração",
                borderColor = Yellow,
                onStartClick = { onStudyClick() }
            )

            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Multiplicação",
                borderColor = Blue800,
                onStartClick = { onStudyClick() }
            )

            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Divisão",
                borderColor = Pink,
                onStartClick = { onStudyClick() }
            )
        }
    }
}

@Preview
@Composable
private fun TheoriesScreenPreview() {
    EducaMatTheme(OrangeColorScheme) {
        TheoriesScreen { }
    }
}
