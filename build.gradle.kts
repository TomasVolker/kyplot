import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.0-rc-146"
}

group = "perchanegro"
version = "0.1"

repositories {

    mavenCentral()
    jcenter()

    maven { url = uri("http://dl.bintray.com/kotlin/kotlin-eap") }

    maven { url = uri("http://dl.bintray.com/kyonifer/maven") }

}

dependencies {

    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit", "junit", "4.12")

    testImplementation(group = "com.kyonifer", name = "koma-core-ejml", version = "0.12")

    implementation(files("libs/kyscript-0.1.jar"))
    implementation(files("libs/kyscript-0.1-sources.jar"))

}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}