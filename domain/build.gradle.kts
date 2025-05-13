plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.ksp)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    // Paging3 "PagingData" 사용을 위함 <- 안드로이드 의존적이지 않은 라이브러리
    implementation(libs.androidx.paging.common)

    // Json 직렬화
    implementation(libs.kotlinx.serialization.json)

    // Java dagger
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}