package com.example.feature

import com.example.controller.TestController
import com.example.controller.UserController
import io.ktor.routing.*

fun Routing.test(testController: TestController) {
	route("test") {
		get { testController.test(this.context) }

	}
}

fun Routing.user(userController: UserController) {
	route("user") {
		get { userController.getUserByEmail(this.context) }
		post { userController.create(this.context) }
		put { userController.update(this.context) }
	}
}