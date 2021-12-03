package com.example

import com.example.config.setupApp
import io.ktor.server.engine.*

@OptIn(EngineAPI::class)
fun main() {
	setupApp().start(wait = true)
}
