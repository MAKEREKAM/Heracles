plugins {
    kotlin("jvm") version "2.0.20"
    id("io.ktor.plugin") version "3.0.0-rc-1"
}

group = "kr.vanilage"
version = "1.0"

val buildPath = "D:\\Programming\\Mcserver\\Heracles\\plugins"

application {
    mainClass.set("kr.vanilage.main.Main")
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("net.dv8tion:JDA:5.1.0")
    implementation("club.minnced:discord-webhooks:0.8.4")
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-netty")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("io.ktor:ktor-server-config-yaml")
    testImplementation("io.ktor:ktor-server-test-host-jvm")
}

task("copy") {
    doLast {
        val directory = File(buildPath)

        if (directory.exists() && directory.isDirectory) {
            if (directory.listFiles().any {it.name == project.name + "-all.jar"}) {
                copy {
                    from("build/libs") // 파일이 나오는 주소를 설정하세요.
                    include("*.jar")
                    into(buildPath.plus("\\update"))
                }

                delete(buildPath.plus("\\update\\RELOAD"))
            }

            else {
                copy {
                    from("build/libs") // 파일이 나오는 주소를 설정하세요.
                    include("*.jar")
                    into(buildPath)
                }

                delete(buildPath.plus("\\update\\RELOAD"))
            }
        }
    }
}

tasks {
    named("shadowJar") {
        finalizedBy("copy")
    }
}