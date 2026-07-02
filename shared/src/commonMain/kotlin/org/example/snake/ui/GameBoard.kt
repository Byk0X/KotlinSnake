package org.example.snake.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.snake.Point


@Composable
fun GameBoard(snake: List<Point>, food: Point, cols: Int, rows: Int) {

    Canvas(
        modifier = Modifier
            .fillMaxSize().padding(16.dp)
    ) {

        println(size)
        val cellSize = minOf(
            size.width / cols,
            size.height / rows
        )

        val boardWidth = cellSize * cols
        val boardHeight = cellSize * rows

        val offsetX = (size.width - boardWidth) / 2
        val offsetY = (size.height - boardHeight) / 2

        // rysowanie planszy
        drawRect(
            color = Color.Black,
            topLeft = Offset(offsetX, offsetY),
            size = Size(boardWidth, boardHeight),
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f)
        )

        // rysowanie węża
        snake.forEach {
            drawRect(
                color = Color.Green,
                topLeft = Offset(offsetX + it.getX() * cellSize, offsetY + it.getY() * cellSize),
                size = Size(cellSize, cellSize)
            )
        }

        // rysowanie owocu
        drawRect(
            color = Color.Red,
            topLeft = Offset(offsetX + food.getX() * cellSize, offsetY + food.getY() * cellSize),
            size = Size(cellSize, cellSize)
        )

    }

}