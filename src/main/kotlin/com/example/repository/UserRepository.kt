package com.example.repository

import com.example.model.dto.UserDTO
import com.example.model.entity.Users
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UserRepository {
	init {
		transaction {
			SchemaUtils.create(Users)
		}
	}

	fun findByEmail(email: String): UserDTO? {
		return transaction {
			Users.select { Users.email eq email }
				.map { Users.toDTO(it) }
				.firstOrNull()
		}
	}

	fun findByUsername(username: String): UserDTO? {
		return transaction {
			Users.select { Users.username eq username }
				.map { Users.toDTO(it) }
				.firstOrNull()
		}
	}

	fun create(user: UserDTO): Long? {
		return transaction {
			Users.insertAndGetId { row ->
				row[email] = user.email
				row[username] = user.username!!
				row[password] = user.password!!
				row[bio] = user.bio
				row[image] = user.image
			}.value
		}
	}

	fun update(email: String, user: UserDTO): UserDTO? {
		transaction {
			Users.update({ Users.email eq email }) { row ->
				row[Users.email] = user.email
				if (user.username != null) {
					row[username] = user.username
				}
				if (user.password != null) {
					row[password] = user.password
				}
				if (user.bio != null) {
					row[bio] = user.bio
				}
				if (user.image != null) {
					row[image] = user.image
				}
			}
		}
		return findByEmail(user.email)
	}
}
