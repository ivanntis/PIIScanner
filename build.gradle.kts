plugins {
    id("java")
    application // Agregar el plugin de aplicación

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("net.portswigger.burp.extensions:montoya-api:2024.7")

}

application {
    mainClass.set("org.example.ExpressionLoggerExtension") // Define la clase principal
}

tasks.jar {
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

tasks.test {
    useJUnitPlatform()
}