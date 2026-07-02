package org.example.snake

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Fruit {

    var position: Point by mutableStateOf(Point(0, 0))
        private set


    fun spawnFruit(point: Point) {

        position = point
    }

}