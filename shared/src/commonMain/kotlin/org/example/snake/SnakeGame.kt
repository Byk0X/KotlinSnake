package org.example.snake

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

class SnakeGame {

    val rows: Int = 40
    val cols: Int = 60
    var score by mutableStateOf(0)
        private set

    var pause by mutableStateOf(false)
        private set

    var direction by mutableStateOf(Direction.STOP)
        private set

    var snake by mutableStateOf(Snake(rows, cols))
    val fruit by mutableStateOf(Fruit())

    var gameOver by mutableStateOf(false)
        private set

    init {
        fruit.spawnFruit(generateNewFruitPosition())
    }

    fun changeDirection(newDirection: Direction) {

        // can't perform 180 rotation
        when {
            direction == Direction.DOWN && newDirection == Direction.UP -> return
            direction == Direction.LEFT && newDirection == Direction.RIGHT -> return
            direction == Direction.UP && newDirection == Direction.DOWN -> return
            direction == Direction.RIGHT && newDirection == Direction.LEFT -> return
        }

        direction = newDirection
    }

    fun addScore() {
        this.score += 1
    }

    suspend fun gameloop() {
        var grow: Boolean = false
        while (true) {

            delay(100.milliseconds)

            if (pause) {
                continue
            }



            if (shouldEatFruit()) {

                fruit.spawnFruit(generateNewFruitPosition())
                addScore()
                grow = true

            } else {
                grow = false
            }
            snake.move(direction, grow)

            if (snake.hasBitItself()) {
                gameOver = true
            }
        }

    }

    fun shouldEatFruit(): Boolean {

        return snake.getHead() == fruit.position

    }


    fun generateNewFruitPosition(): Point {

        while (true) {

            val newFruitPosition = Point(Random.nextInt(0, cols), Random.nextInt(0, rows))

            if (newFruitPosition !in snake.body) {
                return newFruitPosition
            }

        }

    }

    fun pauseGame() {
        pause = !pause
    }

    fun getGameState(): Boolean {
        return this.pause
    }

    fun isGameOver(): Boolean {
        return this.gameOver
    }

    fun restartGame() {
        gameOver = false
        score = 0
        fruit.spawnFruit(generateNewFruitPosition())
        snake.reset(rows, cols)
        direction = Direction.STOP
    }

}