package com.example.model.entity

import com.example.model.dto.UserDTO
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow

object Users : LongIdTable() {
	val email: Column<String> = varchar("email", 200).uniqueIndex()
	val username: Column<String> = varchar("username", 100).uniqueIndex()
	val password: Column<String> = varchar("password", 150)
	val bio: Column<String?> = varchar("bio", 1000).nullable()
	val image: Column<String?> = varchar("image", 255).nullable()

	fun toDTO(row: ResultRow): UserDTO {
		return UserDTO(
			id = row[id].value,
			email = row[email],
			username = row[username],
			password = row[password],
			bio = row[bio],
			image = row[image]
		)
	}
}