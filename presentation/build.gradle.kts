/*
 * Copyright 2023 DroidconKE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    id("droidconke.android.library")
    id("droidconke.android.hilt")
    id("droidconke.android.library.compose")
}

android {
    namespace = "ke.droidcon.kotlin.presentation"

    defaultConfig {
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    kotlinOptions {
        freeCompilerArgs + "-Xjvm-default=all"
    }

    buildFeatures {
        buildConfig = true
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            pickFirsts.add("META-INF/io.netty.versions.properties")
        }
    }

    /**
     * Excluding the folder as it has classes from Google AOSP which don't pass most rules
     */
    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        exclude("**/com/android254/presentation/common/bottomsheet/**")
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":chai"))

    implementation(libs.android.appCompat)
    implementation(libs.android.material)
    implementation(libs.lifecycle.runtimeKtx)
    implementation(libs.timber)
    implementation(libs.androidx.splashscreen)
    implementation(libs.kotlin.coroutines.play.services)
    implementation(libs.gms.play.services.auth)
    implementation(libs.lottie.compose)
    implementation(libs.gson.gson)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.kotlin.coroutines.datetime)

    testImplementation(libs.test.robolectric)
    testImplementation(libs.android.test.espresso)
    testImplementation(libs.test.navigation)
    testImplementation(libs.test.mockk)
}

kotlin {
    sourceSets {
        all {
            languageSettings.apply {
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
            }
        }
    }
}