package ru.hse.financialdetective.data.network


import okhttp3.Interceptor
import okhttp3.Response
import ru.hse.coursework.financialdetective.BuildConfig

/**
 * Отвечает за добавление JWT-токена авторизации при запросах
 */
class JWTInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val token = BuildConfig.API_TOKEN

        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        val response = chain.proceed(modifiedRequest)

        return response
    }
}
