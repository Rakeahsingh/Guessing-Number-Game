package com.example.guessingnumbergame.gameScreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {

    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    fun onEvent(event: GameEvent){
        when(event){
            is GameEvent.SubmitButtonClick -> {
                onUserInput(userNo = event.value, context = event.context)
            }
            is GameEvent.TryAgainClick -> {
                _state.value = GameState()
            }
            is GameEvent.UpdateUserNo -> {
                _state.update {
                    it.copy(
                        userNo = event.value
                    )
                }
            }
        }
    }


    private fun onUserInput(userNo: String, context: Context){
        viewModelScope.launch {
            val userNumberInt = try {
                userNo.toInt()
            }catch (e: Exception){
                0
            }


            if (userNumberInt !in 1..99){
                Toast.makeText(context, "Please Enter A Valid Number", Toast.LENGTH_SHORT).show()

            }

            val currentState = _state.value

            val newGuessingNumberLeft = currentState.userChanceLeft - 1

            val newGameState = when{
                userNumberInt == currentState.mysteriousNumber -> GameStates.WON
                newGuessingNumberLeft == 0 -> GameStates.LOOSE
                else -> GameStates.PLAYING
            }

            val newHintDescription = when{
                userNumberInt > currentState.mysteriousNumber -> {
                    "Hint \n You are Guessing Bigger Number then The Mysterious Number"
                }

                userNumberInt < currentState.mysteriousNumber -> {
                    "Hint \n You are Guessing Smaller Number then The Mysterious Number"
                }
                else -> ""
            }

            val newGuessingList = currentState.numberGuessingList.plus(userNumberInt)

            _state.update {
                it.copy(
                    userNo = "",
                    userChanceLeft = newGuessingNumberLeft,
                    numberGuessingList = newGuessingList,
                    hintDisplaying = newHintDescription,
                    gameStates = newGameState
                )
            }

        }

    }

}