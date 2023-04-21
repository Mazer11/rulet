package edu.mazer.casino.ui.screens.rulescreen

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import edu.mazer.casino.R
import edu.mazer.casino.utils.rulette.RuletRules
import edu.mazer.casino.utils.rulette.RuletteColor
import edu.mazer.casino.utils.rulette.RulettePrediction
import kotlin.math.roundToInt

@Composable
fun RuleScreen() {

    val context = LocalContext.current
    val number = remember { mutableStateOf(0) }
    val score = remember { mutableStateOf(10) }
    val predictionNumberText = remember { mutableStateOf("") }
    val prediction = remember { mutableStateOf(RulettePrediction()) }
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val buttonEnabled = remember { mutableStateOf(true) }
    val isCorrectPrediction = remember {
        derivedStateOf {
            prediction.value.color != null ||
                    prediction.value.number != null
        }
    }
    val focusManager = LocalFocusManager.current
    val rotationValue = remember { mutableStateOf(0f) }
    val angle = animateFloatAsState(
        targetValue = rotationValue.value,
        animationSpec = tween(durationMillis = 3000),
        finishedListener = {
            val index = (360 - (it % 360)) / (360f / RuletRules.list.size)
            val result = RuletRules.list[index.roundToInt()]
            number.value = result.number
            if (prediction.value.color != null)
                if (prediction.value.color == result.color)
                    score.value += 2
                else
                    score.value -= 2
            if (prediction.value.number != null) {
                if (prediction.value.number == 0 && result.number == 0)
                    score.value += 10
                else
                    score.value -= 10
                if (prediction.value.number == result.number)
                    score.value += 4
                else
                    score.value -= 4
            }
            buttonEnabled.value = true
            prediction.value = RulettePrediction()
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
                    fontSize = 24.sp,
                    color = Color.White
                )
                Text(
                    text = "Score: ${score.value}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Box(
                modifier = Modifier
                    .size((screenWidth + 24).dp)
                    .padding(top = 16.dp)
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Column {
                    IconButton(
                        onClick = {
                            prediction.value = prediction.value.copy(
                                color = RuletteColor.Red
                            )
                        },
                        enabled = buttonEnabled.value,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .border(
                                width = 1.dp,
                                color = Color.White,
                                shape = CircleShape
                            )
                            .background(
                                color = if (prediction.value.color == RuletteColor.Red)
                                    MaterialTheme.colorScheme.tertiary
                                else
                                    Color.Transparent,
                                shape = CircleShape
                            )
                    ) {
                        Text(
                            text = "R",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    }
                    IconButton(
                        onClick = {
                            prediction.value = prediction.value.copy(
                                color = RuletteColor.Black
                            )
                        },
                        enabled = buttonEnabled.value,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .border(
                                width = 1.dp,
                                color = Color.White,
                                shape = CircleShape
                            )
                            .background(
                                color = if (prediction.value.color == RuletteColor.Black)
                                    Color.Black
                                else
                                    Color.Transparent,
                                shape = CircleShape
                            )
                    ) {
                        Text(
                            text = "B",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    }
                }
                Column(
                    modifier = Modifier.padding(start = 32.dp)
                ) {
                    BasicTextField(
                        value = predictionNumberText.value,
                        onValueChange = { newString ->
                            if (newString.length < 3)
                                predictionNumberText.value = newString
                        },
                        textStyle = TextStyle(
                            fontSize = 24.sp,
                            color = Color.White
                        ),
                        enabled = buttonEnabled.value,
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        modifier = Modifier
                            .width(100.dp)
                            .border(
                                width = 1.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 4.dp)
                    )
                    Button(
                        onClick = {
                            if (predictionNumberText.value != "") {
                                if (predictionNumberText.value.isDigitsOnly()) {
                                    if (predictionNumberText.value.toInt() in 0..36)
                                        prediction.value = prediction.value.copy(
                                            number = predictionNumberText.value.toInt()
                                        )
                                    else
                                        Toast.makeText(
                                            context,
                                            "Only roulette values",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                } else
                                    Toast.makeText(
                                        context,
                                        "Only digits",
                                        Toast.LENGTH_SHORT
                                    ).show()
                            } else
                                Toast.makeText(
                                    context,
                                    "Wrong value",
                                    Toast.LENGTH_SHORT
                                ).show()

                        },
                        enabled = buttonEnabled.value,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(
                            text = "Bet"
                        )
                    }
                }
            }

            Button(
                onClick = {
                    rotationValue.value += (720..1079).random().toFloat()
                    buttonEnabled.value = false
                },
                enabled = buttonEnabled.value && isCorrectPrediction.value,
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

    SideEffect {
        if (score.value <= 0)
            Toast.makeText(
                context,
                "YOU LOSE",
                Toast.LENGTH_LONG
            ).show()
    }

}























