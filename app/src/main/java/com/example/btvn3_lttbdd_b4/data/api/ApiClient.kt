package com.example.btvn3_lttbdd_b4.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/") // bạn đổi URL ở đây
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val taskApi: TaskService by lazy {
        Retrofit.Builder()
            .baseUrl("https://amock.io/api/researchUTH/") // bạn đổi URL ở đây
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskService::class.java)
    }
}