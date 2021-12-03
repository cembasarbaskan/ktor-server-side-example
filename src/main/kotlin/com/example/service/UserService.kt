package com.example.service

import com.example.model.dto.UserDTO
import com.example.model.exceptions.NotFoundException
import com.example.repository.UserRepository


class UserService(private val userRepository: UserRepository) {

	fun create(user: UserDTO): UserDTO {
		userRepository.findByEmail(user.email).apply {
			require(this == null) { "Email already registered!" }
		}
		userRepository.create(user)
		return user
	}

	fun getByEmail(email: String): UserDTO {
		val user = userRepository.findByEmail(email)
		user ?: throw NotFoundException("UserDTO not found to get.")
		return user
	}


	fun update(email: String, user: UserDTO): UserDTO? {
		return userRepository.update(email, user)
	}

}