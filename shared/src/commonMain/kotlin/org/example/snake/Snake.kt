package org.example.snake

import androidx.compose.runtime.mutableStateListOf

class Snake(private val rows: Int, private val cols: Int) {

    val body = mutableStateListOf(Point(cols / 2, rows / 2))

    fun getHead(): Point {
        return this.body[0]
    }

    fun move(direction: Direction, grow: Boolean = false) {

        val head: Point = body.first()

        var newHead: Point = when (direction) {
            Direction.UP -> Point(head.getX(), head.getY() - 1)
            Direction.DOWN -> Point(head.getX(), head.getY() + 1)
            Direction.LEFT -> Point(head.getX() - 1, head.getY())
            Direction.RIGHT -> Point(head.getX() + 1, head.getY())
            Direction.STOP -> return
        }

        newHead = wrap(newHead)

        body.add(0, newHead)

        if (!grow) {
            body.removeLast()
        }


    }

    private fun wrap(newHead: Point): Point {

        val x = (newHead.getX() + this.cols) % this.cols
        val y = (newHead.getY() + this.rows) % this.rows

        return Point(x, y)
    }

    fun hasBitItself(): Boolean {

        val head: Point = getHead()

        val bodyWithoutHead: List<Point> = body.toList().drop(1)

        if (head in bodyWithoutHead) {
            return true
        }

        return false
    }

    fun reset(rows: Int, cols: Int) {
        body.clear()
        body.add(Point(cols / 2, rows / 2))
    }

}