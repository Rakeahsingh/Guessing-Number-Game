package com.example.guessingnumbergame.gameScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guessingnumbergame.gameScreen.GameState
import com.example.guessingnumbergame.ui.theme.BlueDark
import com.example.guessingnumbergame.ui.theme.YellowDark
import kotlinx.coroutines.delay

@Composable
fun GameScreenComponent(
    state: GameState,
    onSubmitButtonClick: () -> Unit,
    onValueChange: () -> Unit
) {

    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(key1 = Unit){
        delay(500)
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueDark)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Text(
            text = "Number Guessing Game",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = buildAnnotatedString {
                append("No. of Guess Left: ")
                withStyle(
                    SpanStyle(
                        color = Color.White
                    )
                ){
                    append(state.userChanceLeft.toString())
                }
            },
            color = YellowDark,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            state.numberGuessingList.forEach { number ->
                Text(
                    text = number.toString(),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = YellowDark,
                    modifier = Modifier.padding(end = 20.dp)
                )
            }

        }

        Text(
            text = state.hintDisplaying,
            color = Color.White,
            fontSize = 22.sp,
            fontStyle = FontStyle.Italic,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = state.userNo,
            onValueChange = {
                onValueChange()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .focusRequester(focusRequester)
                .bounceClick(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedBorderColor = Color.Transparent,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {  }
            ),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 38.sp,
                color = YellowDark
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Button(
            onClick = {
                onSubmitButtonClick()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = YellowDark
            ),
            modifier = Modifier
                .bounceClick()
                .align(Alignment.End)
                .padding(end = 50.dp, bottom = 200.dp)
        ) {
            Text(
                text = "Submit",
                color = Color.White,
                fontSize = 18.sp
            )
        }

    }

}

