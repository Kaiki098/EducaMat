package br.com.kbat.educamat.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.presentation.theme.Blue
import br.com.kbat.educamat.presentation.theme.EducaMatTheme

@Composable
fun OperationButton(
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit,
    borderColor: Color,
    text: String
) {

    Button(
        modifier = modifier,
        onClick = { onStartClick() },
        shape = RoundedCornerShape(10),
        colors = ButtonColors(
            containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContentColor = Color.White,
            disabledContainerColor = Color.White
        ),
        border = BorderStroke(10.dp, color = borderColor)
    ) {
        Text(
            text = text,
            fontSize = 32.sp,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun OperationButtonPreview() {
    EducaMatTheme {
        OperationButton(
            modifier = Modifier
                .height(150.dp)
                .width(300.dp),
            onStartClick = { },
            borderColor = Blue,
            text = "Azul"
        )
    }
}