import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "2.0.0"

    id("com.github.node-gradle.node") version "7.0.2"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.sean_tay.kotlin_react"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// server

dependencies {
    implementation(platform("org.http4k:http4k-bom:${findProperty("http4kVersion")}"))
    implementation("org.http4k:http4k-core")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${findProperty("jacksonVersion")}")

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

// ui

node {
    download.set(true)
    nodeProjectDir.set(project.file("../ui"))
}

extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}

tasks.register<Sync>("moveUiDist") {
    from(project.file("../ui/dist"))
    into(project.file("./src/main/resources/public"))
}

tasks.findByName("npm_run_build")?.dependsOn("npmInstall")
tasks.findByName("moveUiDist")?.dependsOn("npm_run_build")

tasks.create("buildUi").dependsOn("moveUiDist")

// uberJar

tasks.named<ShadowJar>("shadowJar") {
    archiveBaseName.set("server")
    mergeServiceFiles()
    manifest {
        attributes(mapOf("Main-Class" to "${rootProject.group}.MainKt"))
    }
}