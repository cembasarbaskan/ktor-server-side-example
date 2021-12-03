package com.example.controller

import com.example.model.dto.UserDTO
import com.example.service.UserService
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*

class UserController(private val userService: UserService) {

	suspend fun create(ctx: ApplicationCall) {
		ctx.receive<UserDTO>().apply {
			userService.create(this).apply {
				ctx.respond(this)
			}
		}
	}

	suspend fun getUserByEmail(ctx: ApplicationCall) {
		val email = ctx.parameters["email"]
		val userDTO: UserDTO = email.let {
			require(!it.isNullOrBlank()) { "UserDTO not logged or with invalid email." }
			userService.getByEmail(it)
		}

		ctx.respond(userDTO)
	}

	suspend fun update(ctx: ApplicationCall) {
		ctx.receive<UserDTO>().apply {
			userService.update("test@test.com", this).apply {
				ctx.respond(this!!)
			}
		}
	}
}
