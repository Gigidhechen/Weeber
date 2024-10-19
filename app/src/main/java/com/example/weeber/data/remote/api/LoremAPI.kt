package com.example.weeber.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import rx.Observable

interface LoremAPI {
    @Headers("X-Api-Key: oypooR1GfGr4uHErdP37eA==qZKovWNzxLQpyz2h")
    @GET("?paragraphs=1")
    fun getDescription(): Observable<String>
}