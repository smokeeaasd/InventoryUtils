plugins {
    kotlin("jvm") version "2.0.0"
    id("maven-publish")
}

group = "dev.lucas"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    implementation("io.papermc:paperlib:1.0.7")
    implementation(kotlin("stdlib"))
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("InventoryUtils")
                description.set("A simple inventory UI library for Bukkit")
                url.set("https://github.com/smokeeaasd/InventoryUtils")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("smk05lcs")
                        name.set("Lucas")
                        email.set("smokeesz.dev@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/smokeeaasd/InventoryUtils.git")
                    developerConnection.set("scm:git:ssh://github.com/smokeeaasd/InventoryUtils.git")
                    url.set("http://github.com/smokeeaasd/InventoryUtils")
                }
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}