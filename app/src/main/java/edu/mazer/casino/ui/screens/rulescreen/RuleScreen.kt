package edu.mazer.casino.ui.screens.rulescreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.mazer.casino.R
import edu.mazer.casino.utils.RuletRules
import kotlin.math.roundToInt

@Composable
fun RuleScreen() {

    val number = remember { mutableStateOf(0) }
    val score = remember { mutableStateOf(0) }
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val buttonEnabled = remember { mutableStateOf(true) }
    val rotationValue = remember { mutableStateOf(0f) }
    val angle = animateFloatAsState(
        targetValue = rotationValue.value,
        animationSpec = tween(durationMillis = 3000),
        finishedListener = {
            val index = (360 - (it % 360)) / (360f / RuletRules.list.size)
            number.value = RuletRules.list[index.roundToInt()]
            buttonEnabled.value = true
        }
    )

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = "Result: ${number.value}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = Color.White
                )
                Text(
                    text = "Score: ${score.value}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = Color.White
                )
            }
            Box(
                modifier = Modifier.size((screenWidth + 24).dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ruletka),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .rotate(angle.value)
                )
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .align(Alignment.TopCenter)
                )
            }
            Button(
                onClick = {
                    rotationValue.value += (720..1079).random().toFloat()
                    buttonEnabled.value = false
                },
                enabled = buttonEnabled.value,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(.5f)
            ) {
                Text(text = "Start")

            }

        }

    }

}























