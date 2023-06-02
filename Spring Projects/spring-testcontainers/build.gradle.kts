plugins {
	java
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.kchandrakant.testcontainets"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("io.projectreactor:reactor-core:3.5.6")

	testImplementation ("org.junit.jupiter:junit-jupiter:5.8.1")
	testImplementation ("org.testcontainers:testcontainers:1.18.2")
	testImplementation ("org.testcontainers:junit-jupiter:1.18.2")
	testImplementation("org.testcontainers:mongodb:1.18.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
