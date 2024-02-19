/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }

    maven {
        url = uri("https://jitpack.io")
    }

    maven {
        url = uri("https://repo.inventivetalent.org/content/groups/public/")
    }

    maven {
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api("org.bstats:bstats-base:3.0.0")
    api("org.bstats:bstats-bukkit:3.0.0")
    api("org.inventivetalent.spiget-update:bukkit:1.4.3-SNAPSHOT")
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
}

group = "de.erethon"
version = "1.3.0"
description = "Bedrock"
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveFileName.set("bedrock-${version}.jar")
        dependencies {
            include(dependency("org.bstats:bstats-base:3.0.0"))
            include(dependency("org.bstats:bstats-bukkit:3.0.0"))
            include(dependency("org.inventivetalent.spiget-update:bukkit:1.4.3-SNAPSHOT"))
        }
        relocate("org.bstats", "de.erethon.bedrock.bstats")
        relocate("org.inventivetalent.update.spiget", "de.erethon.bedrock.spiget")
    }
}


tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
    options.compilerArgs.add("--enable-preview")
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
