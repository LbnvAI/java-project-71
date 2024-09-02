project.setProperty("mainClassName", "hexlet.code.App")

plugins {
    id("java")
    application
    //id("com.github.johnrengelman.shadow") version "8.1.1"
    checkstyle
    jacoco
    //id("com.adarshr.test-logger") version "4.0.0"
}

group = "hexlet.code"
version = "1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    testImplementation(platform("org.junit:junit-bom:5.11.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.2")
}

//tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
//    archiveBaseName.set("app") // Устанавливает имя JAR-файла
//    archiveVersion.set("") // Устанавливает версию JAR-файла (пустая строка означает без версии)
//    archiveClassifier.set("") // Устанавливает классификатор JAR-файла (пустая строка означает без классификатора)
//    mergeServiceFiles() // Объединяет файлы сервисов из всех зависимостей в один файл
//}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
    }
}