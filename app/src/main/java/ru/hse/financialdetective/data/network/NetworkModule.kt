package ru.hse.financialdetective.data.network

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import ru.hse.coursework.financialdetective.BuildConfig
import javax.inject.Singleton

/**
 * Отвечает за di для походов в сеть
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideMapper(): ObjectMapper {
        return ObjectMapper().apply {
            registerModule(JavaTimeModule())
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        }
    }

    @Provides
    @Singleton
    fun provideOkHttp(): Call.Factory {
        return OkHttpClient.Builder()
            .addInterceptor(JWTInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttp: Lazy<Call.Factory>, mapper: ObjectMapper): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .callFactory { okHttp.get().newCall(it) }
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}