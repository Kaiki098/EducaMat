package br.com.kbat.educamat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.kbat.educamat.presentation.theme.EducaMatTheme
import br.com.kbat.educamat.presentation.theme.OrangeColorScheme

@Composable
fun NumberPicker(
    modifier: Modifier = Modifier,
    min: Int,
    max: Int,
    size: Int = 30,
    default: Int = min,
    onValueChange: (Int) -> Unit = {}
) {
    var value = default

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy((size / 3).dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(50))
                .background(color = MaterialTheme.colorScheme.primary)
                .size(size.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                modifier = Modifier.fillMaxSize(),
                onClick = {
                    if (value > min) {
                        value--
                        onValueChange(value)
                    }
                }
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Símbolo de subtração",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Text(text = "$value", fontSize = size.sp)
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(50))
                .background(color = MaterialTheme.colorScheme.primary)
                .size(size.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                modifier = Modifier.fillMaxSize(),
                onClick = {
                    if (value < max) {
                        value++
                        onValueChange(value)
                    }
                }
            ) {

                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Default.Add,
                    contentDescription = "Símbolo de adição",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview
@Composable
private fun NumberPickerPreview() {
    EducaMatTheme(OrangeColorScheme) {
        NumberPicker(min = 5, max = 100)
    }
}