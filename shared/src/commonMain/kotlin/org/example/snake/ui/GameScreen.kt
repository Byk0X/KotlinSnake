package org.example.snake.ui

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.*
import org.example.snake.Direction
import org.example.snake.SnakeGame

@Composable
fun GameScreen() {

    val focusRequester = remember { FocusRequester() }
    val game = remember { SnakeGame() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        game.gameloop()

    }

    LaunchedEffect(game.gameOver) {
        if (!game.gameOver) {
            focusRequester.requestFocus()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        if (game.isGameOver()) {
            GameOverScreen(game.score) { game.restartGame() }
        }

        Box(
            modifier = Modifier
                .weight(9f)
                .fillMaxSize()
                .focusRequester(focusRequester)
                .focusable()
                .onKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown) {
                        when (event.key) {
                            Key.DirectionUp -> {
                                game.changeDirection(Direction.UP)
                                true
                            }

                            Key.DirectionDown -> {
                                game.changeDirection(Direction.DOWN)
                                true
                            }

                            Key.DirectionLeft -> {
                                game.changeDirection(Direction.LEFT)
                                true
                            }

                            Key.DirectionRight -> {
                                game.changeDirection(Direction.RIGHT)
                                true
                            }

                            Key.Escape -> {
                                game.pauseGame()
                                true
                            }

                            else -> false
                        }
                    } else {
                        false
                    }
                }

        ) {

            GameBoard(
                snake = game.snake.body,
                food = game.fruit.position,
                cols = game.cols,
                rows = game.rows
            )

            if (game.getGameState()) {
                PauseScreen()
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Score(game.score)
        }
    }


}