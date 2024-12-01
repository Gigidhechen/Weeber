package com.example.weeber.data.remote.api

import retrofit2.http.GET
import rx.Observable

interface NumberAPI {
    @GET("?min=100&max=5000&count=10")
    fun getRandomNumbers(): Observable <ArrayList<Int>>

    @GET("?min=0&max=100&count=10")
    fun getRandomReview(): Observable <ArrayList<Int>>

    @GET("?min=1&max=15&count=10")
    fun getRandomDistance(): Observable <ArrayList<Int>>
}