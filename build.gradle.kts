import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val exclusionList = arrayOf("**Application**",
    "**Request**",
    "**Response**",
    "**Repository**",
    "**Config**",
    "**Exception**",
    "**Error**",
    "**Image**")

plugins {
    id ("org.springframework.boot") version "2.6.4"
    id ("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    id ("org.sonarqube") version "3.3"
    jacoco
    groovy
    kotlin("kapt") version "1.6.20"
}

group = "com.woochamtv"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

kotlin.sourceSets.test {
    kotlin.srcDir("src/test/groovy")
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-validation")

    implementation ("org.springframework.boot:spring-boot-starter-data-mongodb")

    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation ("org.jetbrains.kotlin:kotlin-reflect")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation ("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation ("org.springframework.boot:spring-boot-starter-test")

    testImplementation ("org.spockframework:spock-core:2.1-groovy-3.0")
    testImplementation ("org.spockframework:spock-spring:2.1-groovy-3.0")

    testRuntimeOnly ("org.codehaus.groovy:groovy:3.0.10")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

sonarqube {
    properties {
        property ("sonar.projectKey", "woocham-TV_woocham_TV_backend_api")
        property ("sonar.organization", "woocham-tv")
        property ("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.exclusions", exclusionList.toMutableList())
    }
}

tasks.jacocoTestReport {
    reports {
        html.required.set(true)
        xml.required.set(true)
    }

    executionData(files(exclusionList.toMutableList()))

    finalizedBy(tasks.jacocoTestCoverageVerification)
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            element = "CLASS"
            excludes = exclusionList.toMutableList()
        }
    }
}

jacoco {
    toolVersion = "0.8.7"
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy (tasks.jacocoTestReport)
}
