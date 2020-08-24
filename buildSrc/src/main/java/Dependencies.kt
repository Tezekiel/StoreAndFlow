package com.undabot.storeandflow

object Versions {
    const val compileSdk = 28
    const val targetSdk = 28
    const val minSdk = 22
    const val ktlint = "0.36.0"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.0.0"
    const val androidDesugar = "com.android.tools:desugar_jdk_libs:1.0.5"
    const val googleServicesGradlePlugin = "com.google.gms:google-services:4.3.3"
    const val ktlintGradlePlugin = "org.jlleitschuh.gradle:ktlint-gradle:9.2.1"

    object Kotlin {
        private const val version = "1.3.71"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val androidExtensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
        const val test = "org.jetbrains.kotlin:kotlin-test-junit:$version"
    }

    object Coroutines {
        private const val version = "1.3.6"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val rx2 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val playServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Rx {
        const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.16"
        const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.0.1"
        const val rxBinding = "com.jakewharton.rxbinding2:rxbinding:2.0.0"
    }

    object Koin {
        private const val version = "2.1.5"
        const val koin = "org.koin:koin-android:$version"
        const val koinViewmodel = "org.koin:koin-androidx-viewmodel:$version"
        const val koinScope = "org.koin:koin-androidx-scope:$version"
    }

    object Retrofit {
        private const val version = "2.7.1"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val retrofit_rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
        const val mock = "com.squareup.retrofit2:retrofit-mock:$version"
    }

    object OkHttp {
        private const val version = "4.3.1"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
    }

    object Moshi {
        private const val version = "1.9.2"
        const val moshi = "com.squareup.moshi:moshi:$version"
        const val kotlin = "com.squareup.moshi:moshi-kotlin:$version"
        const val processor = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }

    object Glide {
        private const val version = "4.11.0"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
    }

    object FirebaseVision {
        private const val version = "24.0.1"
        const val vision = "com.google.firebase:firebase-ml-vision:$version"
    }

    object JsonApi {
        private const val version = "0.0.1"
        const val parser = "com.undabot:parser:$version"
        const val retrofit_converter = "com.undabot:retrofit-converter:$version"
        const val gson_adapter = "com.undabot:gson-adapter:$version"
    }


    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.1.0"
        const val android_annotations = "androidx.annotation:annotation:1.1.0"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta4"
        const val cardview = "androidx.cardview:cardview:1.0.0"
        const val swiperefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0-alpha03"
        const val coreKtx = "androidx.core:core-ktx:1.2.0"

        object Lifecycle {
            private const val version = "2.2.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }

        object Navigation {
            private const val version = "2.1.0"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Fragment {
            private const val version = "1.2.0-rc01"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Work {
            private const val version = "2.3.2"
            const val runtimeKtx = "androidx.work:work-runtime-ktx:$version"
        }

        object Test {
            private const val version = "1.2.0"
            const val core = "androidx.test:core:$version"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"

            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
            const val archCoreTesting = "androidx.arch.core:core-testing:2.1.0"
        }
    }

    object Google {
        const val material = "com.google.android.material:material:1.2.0-alpha03"
    }

    object Firebase {
        const val auth = "com.google.firebase:firebase-auth:19.2.0"
        const val dynamicLinks = "com.google.firebase:firebase-dynamic-links:19.1.0"
        const val analytics = "com.google.firebase:firebase-analytics:17.3.0"
    }

    object Test {
        const val junit = "junit:junit:4.12"
        const val assertj = "org.assertj:assertj-core:3.11.1"
        const val kluent = "org.amshove.kluent:kluent:1.59"

        object Mockito {
            private const val version = "2.28.2"
            const val mockito = "org.mockito:mockito-inline:$version"
            const val mockitoAndroid = "org.mockito:mockito-android:$version"
            const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
        }
    }

    object Volley {
        private const val version = "1.1.1"
        const val volley = "com.android.volley:volley:$version"
    }

    object CameraView {
        private const val version = "2.6.1"
        const val camera = "com.otaliastudios:cameraview:$version"
    }

    object Lottie {
        private const val version = "2.8.0"
        const val lottie = "com.airbnb.android:lottie:$version"
    }

    object CircleImageView {
        private const val version = "3.1.0"
        const val circleImageView = "de.hdodenhof:circleimageview:$version"
    }

    const val store = "com.dropbox.mobile.store:store4:4.0.0-alpha03"
    const val gson = "com.google.code.gson:gson:2.8.6"
    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.2"

}