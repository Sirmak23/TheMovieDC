package com.irmak.themoviedc.retrofit

import android.util.Log
import com.irmak.themoviedc.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class RetrofitClient {
    companion object {
        private const val TAG = "RetrofitClient"
        private var INSTANCE: Retrofit? = null

        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        fun getRetrofit(): Retrofit {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                INSTANCE = instance
                instance
            }
        }

        fun getRetrofitJW(): Retrofit {
            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
//                    .baseUrl(BuildConfig.BASE_URL2)
                    .baseUrl("")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                INSTANCE = instance
                instance
            }
        }

        fun <T> enqueueWithRetry(call: Call<T>, callback: Callback<T>) {
            call.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        callback.onResponse(call, response)
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = "Response Error: $errorBody"
                        Log.e(TAG, errorMessage)
                        callback.onFailure(call, Throwable(errorMessage))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    val errorMessage = "Request Failed: ${t.message}"
                    Log.e(TAG, errorMessage)
                    callback.onFailure(call, Throwable(errorMessage))
                }
            })
        }
    }
}

//abstract class RetrofitClient {
//    companion object{
//        @Volatile
//        private var INSTANCE: Retrofit? =null
//
//        fun getRetrofit(): Retrofit {
//            return INSTANCE?: synchronized(this){
//                val instance = Retrofit.Builder()
//                    .baseUrl(BuildConfig.BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}
