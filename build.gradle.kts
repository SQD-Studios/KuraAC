plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "2.3.1"
    id("com.gradleup.shadow") version "9.3.1"
}

group "net.chamosmp"
version "1.0"

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
        name = "opencollabRepositoryMavenSnapshots"
        url = uri("https://repo.opencollab.dev/maven-snapshots")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")// Paper
    compileOnly("com.viaversion:viaversion-api:4.0.0-21w16a") // Replace VERSION ViaVersion
    implementation("net.kyori:adventure-api:4.26.1") // Adventure API
    implementation("net.kyori:adventure-text-minimessage:4.26.1") // Mini Message
    implementation("org.bstats:bstats-bukkit:3.2.1")  //bStats
    //paperweight.paperDevBundle("1.21.11-R0.1-SNAPSHOT")
}

tasks {
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("1.21.11")
    }
}

/* def targetJavaVersion = 25
java {
    def javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
    }
}

tasks.withType(JavaCompile).configureEach {
    options.encoding = "UTF-8"

    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible()) {
        options.release.set(targetJavaVersion)
    }
}

processResources {
    def props = [version: version]
    inputs.properties props
    filteringCharset "UTF-8"
    filesMatching("paper-plugin.yml") {
        expand props
    }
}

 */

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