package com.example.guessingnumbergame.gameScreen

import android.content.Context

sealed class GameEvent {

    data object TryAgainClick: GameEvent()
    data class UpdateUserNo(val value: String): GameEvent()
    data class SubmitButtonClick(val value: String, val context: Context): GameEvent()

}