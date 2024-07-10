plugins {
    kotlin("jvm") version "2.0.0"
}

group = "org.sean_tay.kotlin_react"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.http4k:http4k-bom:${findProperty("http4kVersion")}"))
    implementation("org.http4k:http4k-core")

    testImplementation(kotlin("test"))

    testImplementation(platform("org.junit:junit-bom:${findProperty("junitVersion")}"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")

    testImplementation("org.http4k:http4k-testing-hamkrest")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}