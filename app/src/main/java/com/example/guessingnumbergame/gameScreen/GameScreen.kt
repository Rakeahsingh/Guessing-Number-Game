package com.example.guessingnumbergame.gameScreen


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.guessingnumbergame.R
import com.example.guessingnumbergame.gameScreen.component.GameScreenComponent
import com.example.guessingnumbergame.gameScreen.component.WinOrLoseDialog

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
                onValueChange = { onEvent(GameEvent.UpdateUserNo(state.userNo)) }
            )
        }

        GameStates.WON -> {
            WinOrLoseDialog(
                text = "Congratulation \n You Won",
                buttonText = "Play Again",
                mysteriousNo = state.mysteriousNumber,
                image = painterResource(id = R.drawable.baseline_emoji_events),
                resetButtonClick = { onEvent(GameEvent.TryAgainClick) }
            )
        }

        GameStates.LOOSE -> {
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


