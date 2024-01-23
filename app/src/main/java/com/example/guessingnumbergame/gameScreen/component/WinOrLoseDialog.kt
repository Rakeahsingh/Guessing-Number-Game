package com.example.guessingnumbergame.gameScreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.guessingnumbergame.R
import com.example.guessingnumbergame.ui.theme.BlueDark
import com.example.guessingnumbergame.ui.theme.YellowDark

@Composable
fun WinOrLoseDialog(
    text: String,
    buttonText: String,
    mysteriousNo: Int,
    image: Painter,
    resetButtonClick: () -> Unit
) {

    Dialog(onDismissRequest = { resetButtonClick() }
    ) {

        Column(
            modifier = Modifier
                .size(300.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(YellowDark),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = text,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "The Mysterious Number is $mysteriousNo",
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Cursive,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Image(
                painter = image,
                contentDescription = "image",
                colorFilter = ColorFilter.tint(
                    color = Color.White
                ),
                modifier = Modifier
                    .size(80.dp)
            )

            Button(
                modifier = Modifier
                    .bounceClick(),
                onClick = { resetButtonClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueDark,
                    contentColor = Color.White
                )
            ) {

                Text(
                    text = buttonText,
                    fontSize = 14.sp
                )

            }

        }

    }


}


@Preview
@Composable
fun WinPreview() {

    WinOrLoseDialog(
        text = "Congratulation \n You Won" ,
        buttonText = "Play Again",
        mysteriousNo = 32,
        image = painterResource(id = R.drawable.baseline_emoji_events)
    ) {

    }
}

@Preview
@Composable
fun LoosePreview() {

    WinOrLoseDialog(
        text = "Better Luck \n Next Time" ,
        buttonText = "Try Again",
        mysteriousNo = 95,
        image = painterResource(id = R.drawable.baseline_rowing)
    ) {

    }
}