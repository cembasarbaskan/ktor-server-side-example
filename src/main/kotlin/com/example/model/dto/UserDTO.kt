package com.example.model.dto

import io.ktor.auth.*

data class UserDTO(
    val id: Long? = null,
    val email: String,
    val token: String? = null,
    val username: String? = null,
    val password: String? = null,
    val bio: String? = null,
    val image: String? = null
) : Principal
