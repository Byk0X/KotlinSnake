package org.example.snake

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {

    val windowState = rememberWindowState()

    Window(
        onCloseRequest = ::exitApplication,
        title = "Snake",
        //resizable = false,

    ) {

        LaunchedEffect(windowState.position) {
            val currentSize = windowState.size

            windowState.size = DpSize(currentSize.width + 1.dp, currentSize.height)
            windowState.size = currentSize
        }

        App()
    }
}