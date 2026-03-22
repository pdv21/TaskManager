package com.example.taskmanager.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:4000/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authApi: AuthApi by lazy {
        retrofit.create(AuthApi::class.java)
    }

    val taskApi: TaskApi by lazy{
        retrofit.create(TaskApi::class.java)
    }
    val categoryApi: CategoryApi by lazy{
        retrofit.create(CategoryApi::class.java)
    }
    val userApi: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }

}