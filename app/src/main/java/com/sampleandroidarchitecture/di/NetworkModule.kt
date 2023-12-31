package com.sampleandroidarchitecture.di

import com.sampleandroidarchitecture.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    single {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                hostnameVerifier {_,_ -> true}
            }
            addNetworkInterceptor(Interceptor {
                it.proceed(
                    it.request().newBuilder()
                        .build()
                )
            })
            readTimeout(30, TimeUnit.SECONDS)
            connectTimeout(30, TimeUnit.SECONDS)
        }.build()
    }

    single {
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            isLenient = true
        }
    }

    single {
        createRetrofit()
    }
}

private fun Scope.createRetrofit() =
    Retrofit.Builder()
        .client(get())
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(
            get<Json>()
                .asConverterFactory("application/json".toMediaType())
        )
        .build()

inline fun <reified T> retrofitService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}