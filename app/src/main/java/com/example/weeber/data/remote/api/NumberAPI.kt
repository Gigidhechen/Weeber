package com.example.weeber.data.remote.api

import retrofit2.http.GET
import rx.Observable

interface NumberAPI {
    @GET("?min=100&max=1000&count=5")
    fun getRandomNumbers(): Observable <ArrayList<Int>>
}