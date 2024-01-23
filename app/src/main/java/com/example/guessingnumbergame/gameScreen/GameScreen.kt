package com.example.guessingnumbergame.gameScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.guessingnumbergame.R
import com.example.guessingnumbergame.gameScreen.component.GameScreenComponent
import com.example.guessingnumbergame.gameScreen.component.WinOrLoseDialog
import com.example.guessingnumbergame.ui.theme.BlueDark

@Composable
fun GameScreen(
    state: GameState,
    onEvent: (GameEvent) -> Unit
) {


    val context = LocalContext.current

    when(state.gameStates){
        GameStates.PLAYING -> {
            GameScreenComponent(
                state = state,
                onSubmitButtonClick = { onEvent(GameEvent.SubmitButtonClick(state.userNo, context = context)) },
                onEvent = onEvent
            )
        }

        GameStates.WON -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlueDark)
            ) {
                WinOrLoseDialog(
                    text = "Congratulation \n You Won",
                    buttonText = "Play Again",
                    mysteriousNo = state.mysteriousNumber,
                    image = painterResource(id = R.drawable.baseline_emoji_events),
                    resetButtonClick = { onEvent(GameEvent.TryAgainClick) }
                )
            }

        }

        GameStates.LOOSE -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlueDark)
            ){
                WinOrLoseDialog(
                    text = "Better Luck \n Next Time",
                    buttonText = "Try Again",
                    mysteriousNo = state.mysteriousNumber,
                    image = painterResource(id = R.drawable.baseline_rowing),
                    resetButtonClick = { onEvent(GameEvent.TryAgainClick) }
                )
            }

        }

    }

}


