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
import br.com.kbat.educamat.presentation.theme.Pink
import br.com.kbat.educamat.presentation.theme.Red
import br.com.kbat.educamat.presentation.theme.Yellow

@Composable
fun TheoriesScreen(modifier: Modifier = Modifier, onStudyClick: (String) -> Unit) {
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
                onStartClick = { onStudyClick(
                    """
                    A soma é uma operação matemática básica que usamos para juntar dois ou mais números e descobrir o total.
                    Para somar números, usamos a técnica de somar colunas
                    
                    Alinhamos unidades com unidades, dezenas com dezenas e assim por diante, e somamos cada coluna separadamente
                    
                    Caso a soma da coluna seja maior que 10, colocamos o número da unidade no resultado e levamos o número da dezena para a soma da próxima coluna
                    
                    Por exemplo: 23+47
                    
                    Somamos as unidades:
                    3+7 = 10
                    
                    Colocamos o 0 no resultado e levamos o 1 para a soma das dezenas:
                    2+4+1 = 7
                    
                    E no final temos:
                    23+47 = 70
                    """.trimIndent()
                ) }
            )

            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Subtração",
                borderColor = Yellow,
                onStartClick = { onStudyClick(
                    """
                    Para subtrair números, usamos a técnica de somar colunas
                    
                    Alinhamos unidades com unidades, dezenas com dezenas e assim por diante, e subtraimos cada coluna separadamente
                    
                    O número da parte superior seja maior que o inferior, pegamos emprestado uma dezena da coluna à esquerda e adicionamos na nossa coluna atual
                    
                    É importante lembrar que no processo de pegar emprestado uma unidade, a unidade da coluna à esquerda se torna uma unidade a menos
                    
                    Por exemplo: 53-37
                    
                    3-7 não é possível, então pegamos uma dezena do 5 e adicionamos ao 3, ficando 13-7
                    13-7 = 6
                    
                    Como pegamos uma dezena do 5, o 5 se torna 4.
                    Então subtraímos as dezenas:
                    4-3 = 1
                    E no final temos:
                    53-37 = 16
                    
                    Uma dica pra fazer esse processo mais rapidamente é treinar bastante, até que você consiga fazer a subtração mentalmente
                    """.trimIndent()
                ) }
            )

            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Multiplicação",
                borderColor = Blue800,
                onStartClick = { onStudyClick(
                    """
                    Multiplicação é uma maneira rápida de adicionar o mesmo número várias vezes.
                    
                    Para multiplicar números, inicialmente escrevemos os números um embaixo do outro, alinhados à direita
                    
                    Multiplique cada dígito do segundo número pelo primeiro número, começando pelo dígito da unidades
                    
                    À cada multiplicação, adicionamos um zero à direita do resultado, deslocando o número uma casa para a esquerda
                    
                    Por exemplo: 23x17
                    Começamos multiplicando 3 por 17, que resulta em 51
                    3*17 = 51
                    
                    Então multiplicamos 2 por 17, que resulta em 34
                    2x17 = 34
                    
                    Por fim, somamos os resultados como na soma, deslocando o 34 uma casa para a esquerda:
                    51 + 340 = 391
                    
                    Dica: para fazer a multiplicação mais rapidamente, treine bastante as tabuadas de 1 a 10, pois facilitam o cálculo de números maiores
                    """.trimIndent()
                ) }
            )

            OperationButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Divisão",
                borderColor = Pink,
                onStartClick = { onStudyClick(
                    """
                    Divisão é uma maneira de descobrir quantas vezes um número cabe dentro de outro número.
                    
                    Chamamos de divisor o número que divide, dividendo o número que é dividido e quociente o resultado da divisão.
                    
                    Existem algoritmos diferentes para calcular a divisão, mas o mais comum é o método de chaves.
                    
                    Para explicar esse método, vamos usar o exemplo 125 dividido por 5
                    
                    Primeiro, escrevemos o 125 dentro de uma chave e o 5 fora da chave:
                    125  |5
                    
                    Então analisamos o primeiro número do dividendo, começando sempre da esquerda para a direita. 
                    No caso o 1, é possível dividi-lo por 5? Se for, realizaremos a divisão. Como 1 é menor que 5, 
                    não é possível; então, vamos selecionar os dois primeiros números — no caso o 12. Como 12 é 
                    maior do que 5, é possível dividir.
                    
                    Depois disso, procuramos qual número que, ao multiplicá-lo por 5, é igual ou chega próximo de 12, não podendo nunca ser maior que 12.
                    Na tabuada do 5, o número mais próximo é 10. Então colocamos 2 no quociente e subtraímos 10 de 12, resultando em 2.
                    125 |5
                    10   2
                     2
                     
                    Em seguida, trazemos o próximo número do dividendo, que é o 5, e colocamos ao lado do 2, formando o número 25.
                    125 |5
                    10   2
                     25
                     
                    Repetimos o processo, procurando qual número multiplicado por 5 é igual ou chega próximo de 25. Conhecendo a tabuada do 5, sabemos que 5x5 = 25.
                    Então colocamos 5 no quociente e subtraímos 25 de 25, resultando em 0.
                    125 |5
                    10   25
                     25
                     25
                      0
                    """.trimIndent()
                ) }
            )
        }
    }
}

@Preview
@Composable
private fun TheoriesScreenPreview() {
    EducaMatTheme {
        TheoriesScreen { }
    }
}