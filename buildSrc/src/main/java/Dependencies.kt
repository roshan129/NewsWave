object Version {
    const val core = "1.8.0"
    const val lifecycle = "2.3.1"
    const val activityCompose = "1.5.1"
    const val compose = "2022.10.00"
    const val junit = "4.13.2"
    const val espresso = "3.5.1"

    //extra added dependencies as per requirement
    const val hilt = "2.44"
    const val hiltNavigationCompose = "1.1.0-alpha01"
    const val lifecycleViewModelCompose = "2.6.1"
    const val room = "2.5.1"
    const val coroutines = "1.6.4"
    const val navigationCompose = "2.5.3"
    const val gson = "2.9.0"
    const val retrofit = "2.9.0"
    const val okhttp = "5.0.0-alpha.2"
    const val okhttpLoggingInterceptor = "4.9.1"
    const val coilCompose = "1.3.2"

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

    const val hilt = "com.google.dagger:hilt-android:${Version.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Version.hiltNavigationCompose}"
    const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.lifecycleViewModelCompose}"
    const val room = "androidx.room:room-runtime:${Version.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    const val navigationCompose = "androidx.navigation:navigation-compose:${Version.navigationCompose}"
    const val gson = "com.google.code.gson:gson:${Version.gson}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttpLoggingInterceptor}"
    const val coilCompose = "io.coil-kt:coil-compose:${Version.coilCompose}"

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