object Version {
    const val core = "1.8.0"
    const val lifecycle = "2.3.1"
    const val activityCompose = "1.5.1"
    const val compose = "2022.10.00"
    const val junit = "4.13.2"
    const val espresso = "3.5.1"
}

object Deps {
    const val core = "androidx.core:core-ktx:${Version.core}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
    const val activityCompose = "androidx.activity:activity-compose:${Version.activityCompose}"
    const val composeBom = "androidx.compose:compose-bom:${Version.compose}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial3 = "androidx.compose.material3:material3"

}

object TestImplementation {
    const val junit = "junit:junit:${Version.junit}"

}

object AndroidTestImplementation {
    const val testExtJunit = "androidx.test.ext:junit:${Version.junit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espresso}"
    const val composeUiTestJunit4 = "androidx.compose.ui:ui-test-junit4"

}

object DebugImplementation {
    const val debugComposeUiTooling = "androidx.compose.ui:ui-tooling"
    const val debugComposeUiTestManifest = "androidx.compose.ui:ui-test-manifest"

}