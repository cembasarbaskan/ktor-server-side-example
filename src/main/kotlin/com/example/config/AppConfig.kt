package com.example.config

import com.example.controller.TestController
import com.example.controller.UserController
import com.example.feature.test
import com.example.feature.user
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.kodein.di.generic.instance

const val SERVER_PORT = 8080

@EngineAPI
fun setupApp(): BaseApplicationEngine {
	DbConfig.setup("jdbc:h2:mem:DATABASE_TO_UPPER=false;", "sa", "")
	return server(CIO)
}

@EngineAPI
fun server(
	engine: ApplicationEngineFactory<BaseApplicationEngine,
		out ApplicationEngine.Configuration>
): BaseApplicationEngine {
	return embeddedServer(
		engine,
		port = SERVER_PORT,
		watchPaths = listOf("mainModule"),
		module = Application::mainModule
	)
}

fun Application.mainModule() {
	val testController by ModulesConfig.kodein.instance<TestController>()
	val userController by ModulesConfig.kodein.instance<UserController>()

	install(CallLogging)
	install(ContentNegotiation) {
		jackson {
		}
	}
//	install(Authentication) {
//		jwt {
//			verifier(JwtProvider.verifier)
//			authSchemes("Token")
//			validate { credential ->
//				if (credential.payload.audience.contains(JwtProvider.audience)) {
//					userController.getUserByEmail(credential.payload.claims["email"]?.asString())
//				} else null
//			}
//		}
//	}

//	install(StatusPages) {
//		exception(Exception::class.java) {
//			val errorResponse = ErrorResponse(mapOf("error" to listOf("detail", this.toString())))
//			context.respond(
//				HttpStatusCode.InternalServerError, errorResponse
//			)
//		}
//	}

	install(Routing) {
		test(testController)
		user(userController)
	}
}
