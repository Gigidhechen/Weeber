package com.example.weeber.data.remote.client

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {

    private val builder = Retrofit.Builder()
        .addCallAdapterFactory(RxJavaCallAdapterFactory.createAsync())
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(
            GsonConverterFactory.create(GsonBuilder().serializeNulls().create()) )

    private val logging = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null

    fun <T> createService(serviceClass: Class<T>,context: Context): T {
        if(retrofit == null) {
            if(okHttpClient == null) {
                okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build()
            }
            retrofit = builder.client(okHttpClient!!).build()
        }
        return retrofit!!.create(serviceClass)
    }
}