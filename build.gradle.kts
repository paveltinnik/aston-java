plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("com.zaxxer:HikariCP:5.1.0")

    implementation("org.mapstruct:mapstruct:1.5.5.Final")

    implementation("org.postgresql:postgresql:42.7.3")

    implementation ("com.google.code.gson:gson:2.10.1")

    compileOnly("javax.servlet:javax.servlet-api:4.0.1")

    testImplementation("org.mockito:mockito-core:5.13.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.testcontainers:postgresql:1.17.6")
    testImplementation("org.testcontainers:junit-jupiter:1.17.6")
}

tasks.test {
    useJUnitPlatform()
}