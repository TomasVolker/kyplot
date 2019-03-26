
plugins {
    kotlin("jvm") version "1.3.21"
    maven
}

group = "tomasvolker"
version = "0.1.0"

repositories {

    mavenCentral()
    jcenter()

    maven { url = uri("http://dl.bintray.com/kyonifer/maven") }
    maven { url = uri("http://dl.bintray.com/tomasvolker/maven") }
    maven { url = uri("https://jitpack.io") }

}

dependencies {

    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit", "junit", "4.12")

    implementation(group = "tomasvolker", name = "numeriko-core", version = "0.0.3")
    implementation(group = "com.github.tomasvolker", name = "kyscript", version = "0.1.0")

    testImplementation(group = "com.kyonifer", name = "koma-core-ejml", version = "0.12")

}
