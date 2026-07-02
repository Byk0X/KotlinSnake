import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(projects.shared)

    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutinesSwing)

    implementation(libs.compose.uiToolingPreview)
}

compose.desktop {
    application {
        mainClass = "org.example.snake.MainKt"

        jvmArgs += listOf(
            "-Dawt.toolkit.name=WLToolkit",  // Kluczowa flaga włączająca natywnego Waylanda w JBR
            "-Dgdk.backend=wayland",
            "-Dsun.java2d.noddraw=true"
        )

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.example.snake"
            packageVersion = "1.0.0"
        }
    }
}