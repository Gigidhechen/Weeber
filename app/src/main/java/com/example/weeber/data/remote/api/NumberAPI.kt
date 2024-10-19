package com.example.weeber.data.remote.api

import com.example.weeber.data.model.NumResponse
import retrofit2.http.GET
import rx.Observable

interface NumberAPI {
    @GET("?min=100&max=1000&count=1")
    fun getRandomNumbers(): Observable <NumResponse>
}