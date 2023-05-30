package com.irmak.themoviedc.retrofit

import com.irmak.themoviedc.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitClient {
    companion object{
        @Volatile
        private var INSTANCE: Retrofit? =null

        fun getRetrofit(): Retrofit {
            return INSTANCE?: synchronized(this){
                val instance = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}