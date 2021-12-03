val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val slf4j_version: String by project
val kodein_version : String by project
val hikaricp_version: String by project
val h2_version : String by project
val exposed_version : String by project
val junit_version : String by project
val unirest_version : String by project

plugins {
	application
	kotlin("jvm") version "1.6.0"
}

group = "com.example"
version = "0.0.1"
application {
	mainClass.set("com.example.ApplicationKt")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("io.ktor:ktor-server-core:$ktor_version")
	implementation("io.ktor:ktor-server-netty:$ktor_version")
	implementation("ch.qos.logback:logback-classic:$logback_version")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
	implementation("org.slf4j:slf4j-simple:$slf4j_version")
	implementation("io.ktor:ktor-server-netty:$ktor_version")
	implementation("io.ktor:ktor-server-cio:$ktor_version")
	implementation("io.ktor:ktor-jackson:$ktor_version")
	implementation("io.ktor:ktor-auth-jwt:$ktor_version")
	implementation("org.kodein.di:kodein-di-generic-jvm:$kodein_version")
	implementation("com.zaxxer:HikariCP:$hikaricp_version")
	implementation("com.h2database:h2:$h2_version")
	implementation("org.jetbrains.exposed:exposed:$exposed_version")

	testImplementation("io.ktor:ktor-server-tests:$ktor_version")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
