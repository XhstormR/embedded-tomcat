import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

version = "1.0-SNAPSHOT"

plugins {
    idea
    application
    kotlin("jvm") version "1.2.50"
    id("org.zeroturnaround.gradle.jrebel") version "1.1.8" //gradlew generateRebel
}

application {
    mainClassName = "com.xhstormr.tomcat.MainKt"
}

repositories {
    maven("http://maven.aliyun.com/nexus/content/groups/public/")
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.apache.tomcat.embed:tomcat-embed-core:9.0.8")
}

tasks {
    withType<JavaExec> {
        dependsOn("generateRebel")
        jvmArgs = listOf(ext["rebelAgent"].toString())
    }

    withType<Jar> {
        version = ""
    }

    withType<Wrapper> {
        gradleVersion = "4.8"
        distributionType = Wrapper.DistributionType.ALL
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.isIncremental = true
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
    }
}
