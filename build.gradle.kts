plugins {
    kotlin("multiplatform") version "2.1.10"
}

kotlin {
    jvm()

    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

tasks.register("test") {
    dependsOn("jvmTest")
}
