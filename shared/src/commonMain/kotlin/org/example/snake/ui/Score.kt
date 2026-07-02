package org.example.snake.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Score(score: Int) {
    Text("Wynik: $score")
}