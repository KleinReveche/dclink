import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "1.3.7"
    id("xyz.jpenilla.run-paper") version "1.0.6"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
}

group = "com.kalimero2.team"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    paperDevBundle("1.19-R0.1-SNAPSHOT")

    implementation(project(":dclink-api"))
    implementation(project(":dclink-core"))
}
tasks {
    assemble {
        dependsOn(reobfJar)
    }
}

bukkit {
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    main = "com.kalimero2.team.dclink.paper.PaperPlugin"
    apiVersion = "1.19"
    authors = listOf("byquanton")
}