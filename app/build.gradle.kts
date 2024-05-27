plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {

    buildFeatures {
        buildConfig = true
    }

    namespace = "com.mycash.yajhaz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mycash.yajhaz"
        minSdk = 24
        targetSdk = 34
        versionCode = 3
        versionName = "1.3.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            isShrinkResources = false
        }
        getByName("release") {
            //signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.txt")
        }
    }

    flavorDimensions.add("version")
    productFlavors {

        create("staging") {
            dimension = "version"
            versionNameSuffix = ".stage"
            applicationIdSuffix = ".stage"
        }


        create("live") {
            dimension = "version"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.preference:preference-ktx:1.2.1")
    implementation("androidx.activity:activity-ktx:1.9.0")

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.20")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //webkit
    implementation("androidx.webkit:webkit:1.11.0")

    // hilt implementation.
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.47")
    kapt("androidx.hilt:hilt-compiler:1.2.0")
    implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")


    // Koin
    implementation("io.insert-koin:koin-android:3.4.0")
    implementation("io.insert-koin:koin-androidx-navigation:3.4.0")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // Navigation component.
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")

    //ViewModels delegation extensions for activity
    implementation("androidx.activity:activity-ktx:1.9.0")
    implementation("androidx.fragment:fragment-ktx:1.7.1")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.0")

    //process-phoenix
    implementation("com.jakewharton:process-phoenix:3.0.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:okhttp-dnsoverhttps:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.2")



    // sdp && ssp of font
    val scalableUnit = "1.1.0"
    implementation("com.intuit.sdp:sdp-android:$scalableUnit")
    implementation("com.intuit.ssp:ssp-android:$scalableUnit")

    //FireBase
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
    implementation("com.google.firebase:firebase-inappmessaging-ktx")
    implementation("com.google.firebase:firebase-inappmessaging-display-ktx")
    implementation("com.google.firebase:firebase-installations-ktx")
    implementation("com.google.firebase:firebase-perf-ktx")
    implementation("com.google.firebase:firebase-config-ktx:22.0.0")


    // timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // encrypted shared preference
    implementation("androidx.security:security-crypto:1.1.0-alpha06")

    // Lottie
    implementation("com.airbnb.android:lottie:6.0.1")

    //Android Remote Debugger
    debugImplementation("com.github.zerobranch.android-remote-debugger:debugger:1.1.2")
    releaseImplementation("com.github.zerobranch.android-remote-debugger:noop:1.1.0")


    //Play Store App Update Libraries
    implementation("com.google.android.play:app-update:2.1.0")
    implementation("com.google.android.play:app-update-ktx:2.1.0")

    //paging3
    val pagingVersion = "3.3.0"
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")

    implementation("androidx.work:work-runtime-ktx:2.9.0")

    //Coil
    implementation("io.coil-kt:coil:2.2.2")
    implementation("io.coil-kt:coil-svg:2.2.2")


    //pluto
    debugImplementation("com.plutolib:pluto:2.0.6")
    releaseImplementation("com.plutolib:pluto-no-op:2.0.6")
    debugImplementation("com.plutolib.plugins:network:2.0.6")
    releaseImplementation("com.plutolib.plugins:network-no-op:2.0.6")


    // Zxing
    implementation("com.google.zxing:core:3.5.0")

    // Lingver
    implementation("com.github.YarikSOffice:lingver:1.3.0")


    //coreLibraryDesugaring
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

}