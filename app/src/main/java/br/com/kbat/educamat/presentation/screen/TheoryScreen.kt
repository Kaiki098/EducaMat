package br.com.kbat.educamat.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.R
import br.com.kbat.educamat.presentation.theme.BlueColorScheme
import br.com.kbat.educamat.presentation.theme.EducaMatTheme

@Composable
fun TheoryScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
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
            Modifier
                .padding(horizontal = 30.dp, vertical = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.3f)
                    .border(
                        width = 10.dp, color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(20)
                    )
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(20)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Teoria", fontSize = 24.sp)
            }


        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TheoryPreview() {
    EducaMatTheme(
        colorScheme = BlueColorScheme
    ) {
        TheoryScreen(onBackClick = {})
    }
}