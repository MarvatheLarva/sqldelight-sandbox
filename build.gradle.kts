import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.0"
    application
}

group = "com.mtlarva.sandbox"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
        maven(url = "https://download.red-gate.com/maven/release")
    }
}

dependencies {
    implementation("com.zaxxer:HikariCP:5.1.0")

    implementation(project(mapOf("path" to ":modules:mysql")))
    implementation(project(mapOf("path" to ":modules:postgres")))

    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("ch.qos.logback:logback-classic:1.5.6")
}

tasks.withType<JavaCompile> {
    options.release = 21
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

application {
    mainClass.set("MainKt")
}
