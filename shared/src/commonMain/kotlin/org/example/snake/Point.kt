package org.example.snake

data class Point(
    private var x: Int,
    private var y: Int
) {


    fun set(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    fun setX(x: Int) {
        this.x = x
    }

    fun setY(y: Int) {
        this.y = y
    }

    fun getX(): Int = x
    fun getY(): Int = y

}