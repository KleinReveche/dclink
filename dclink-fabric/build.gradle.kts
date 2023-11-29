val transitiveInclude: Configuration by configurations.creating {
    // Source - https://gist.github.com/jakobkmar/3c7e68ff57957d647a37ed568e5068c7
    exclude(group = "com.mojang")
    exclude(group = "org.jetbrains.kotlin")
    exclude(group = "org.jetbrains.kotlinx")
}

plugins {
    alias(libs.plugins.fabric.loom)
}

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    minecraft("com.mojang", "minecraft", libs.versions.minecraft.get())
    mappings(loom.officialMojangMappings())
    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)
    modImplementation(libs.adventure.fabric)
    modImplementation(libs.cloud.fabric)

    implementation(project(":dclink-core"))
    include(project(":dclink-core"))
    implementation(project(":dclink-api"))
    include(project(":dclink-api"))

    transitiveInclude(libs.adventure.api)
    transitiveInclude(libs.adventure.minimessage)
    transitiveInclude(libs.configurate.hocon)
    transitiveInclude(libs.jda) {
        exclude(module = "opus-java")
    }
    transitiveInclude(libs.sqlite)
    transitiveInclude(libs.cloud.fabric)
    transitiveInclude(libs.adventure.fabric)

    transitiveInclude.resolvedConfiguration.resolvedArtifacts.forEach {
        include(it.moduleVersion.id.toString())
    }
}

tasks {
    processResources {
        filesMatching("fabric.mod.json"){
            expand(
                "version" to project.version,
                "minecraftVersion" to libs.versions.minecraft.get(),
                "loaderVersion" to libs.versions.fabric.loader.get(),
            )
        }
    }
}