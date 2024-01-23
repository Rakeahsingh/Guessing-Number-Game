package com.example.guessingnumbergame.gameScreen

data class GameState(
    val userNo: String = "",
    val userChanceLeft: Int = 5,
    val numberGuessingList: List<Int> = emptyList(),
    val mysteriousNumber: Int = (1..99).random(),
    val hintDisplaying: String = "Guess \n the mystery number between \n 0 and 100.",
    val gameStates: GameStates = GameStates.PLAYING
)



enum class GameStates{
    WON,
    LOOSE,
    PLAYING
}
