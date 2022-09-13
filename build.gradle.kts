import com.google.protobuf.gradle.*

plugins {
    kotlin("jvm") version "1.7.10"
    id("com.google.protobuf") version "0.8.19"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
//    protobuf(project(":protos"))

    implementation("io.grpc:grpc-kotlin-stub:1.3.0") // YOUR_GRPC_KOTLIN_VERSION
    implementation("io.grpc:grpc-protobuf:1.49.0") // YOUR_GRPC_VERSION
    implementation("com.google.protobuf:protobuf-kotlin:3.21.5") // YOUR_PROTOBUF_VERSION


    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions {
        freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.5"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.49.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.builtins {
                named("java") {
//                    option("lite")
                }
                id("kotlin") {
//                    option("lite")
                }
            }
            it.plugins {
                id("grpc") {
//                    option("lite")
                }
                id("grpckt") {
//                    option("lite")
                }
            }
        }
    }
}

//test {
//    useJUnitPlatform()
//}
//
//compileKotlin {
//    kotlinOptions.jvmTarget = "1.8"
//}
//
//compileTestKotlin {
//    kotlinOptions.jvmTarget = '1.8'
//}