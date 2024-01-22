package com.example.guessingnumbergame.gameScreen

sealed class GameEvent {

    data object TryAgainClick: GameEvent()
    data class UpdateUserNo(val value: String): GameEvent()
    data class SubmitButtonClick(val value: String): GameEvent()

}