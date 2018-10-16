import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.0-rc-146"
}

group = "perchanegro"
version = "1.0-SNAPSHOT"

repositories {

    maven { setUrl("http://dl.bintray.com/kotlin/kotlin-eap") }
    maven { setUrl("https://raw.github.com/aliceinnets/maven-repository/master/") }
    mavenCentral()

    maven { setUrl("http://dl.bintray.com/kyonifer/maven") }
    jcenter()

}

dependencies {

    compile(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")

    compile(group = "aliceinnets", name = "java-jyplot", version = "1.3.0", configuration = "sources")
    compile(group = "aliceinnets", name = "java-jyplot", version = "1.3.0", configuration = "javadoc")

    testCompile(group = "com.kyonifer", name = "koma-core-ejml", version = "0.12")

}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}