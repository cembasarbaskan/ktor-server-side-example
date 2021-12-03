package com.example.config

import com.example.controller.TestController
import com.example.controller.UserController
import com.example.repository.UserRepository
import com.example.service.UserService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

object ModulesConfig {
	private val testModule = Kodein.Module("TEST") {
		bind() from singleton { TestController() }
	}

	private val userModule = Kodein.Module("USER") {
		bind() from singleton { UserController(instance()) }
		bind() from singleton { UserService(instance()) }
		bind() from singleton { UserRepository() }
	}

	internal val kodein = Kodein {
		import(testModule)
		import(userModule)
	}
}
