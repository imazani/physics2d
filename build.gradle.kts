import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion

plugins {
    application
    java
}

java {
    // Ensures compilation targets Java 21 regardless of the system default JDK
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    // Fully qualified name of your main class (we’ll create it below)
    mainClass.set("engine.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 5 for tests (optional but nice to have)
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<JavaCompile> {
    // Emits class files targeted to Java 21 bytecode
    options.release.set(21)
}

tasks.test {
    // Enable JUnit 5
    useJUnitPlatform()
}