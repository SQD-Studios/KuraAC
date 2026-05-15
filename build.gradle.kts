plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "3.0.2"
    id("com.gradleup.shadow") version "9.3.1"
}

group = "net.chamosmp.kuraac"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_25

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "viaversionEverything"
        url = uri("https://repo.viaversion.com/everything")
    }

    maven {
        url = uri("https://repo.opencollab.dev/main/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")// Paper
    compileOnly("com.viaversion:viaversion-api:4.0.0-21w16a") // Replace VERSION ViaVersion
    implementation("org.bstats:bstats-bukkit:3.2.1")  //bStats
    compileOnly("org.geysermc.geyser:api:2.9.5-SNAPSHOT") // GeyserMC
    compileOnly("org.geysermc.floodgate:api:2.2.5-SNAPSHOT")
}

tasks {
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("26.1.2")
    }
}

tasks.shadowJar {
    configurations = project.configurations.runtimeClasspath.map { setOf(it) }

    dependencies {
        // Only merge bStats into the final jar, no other dependencies
        exclude { it.moduleGroup != "org.bstats" }
    }

    // Relocate bStats into the plugin's package to avoid conflicts with other
    // plugins using bStats
    relocate("org.bstats", project.group.toString())
}

//
tasks.processResources {
    val props = mapOf("version" to project.version)
    inputs.properties(props)
    filteringCharset = "UTF-8"

    filesMatching("paper-plugin.yml") {
        expand(props)
    }
}