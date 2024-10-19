package com.example.weeber.data.remote.api

import com.example.weeber.data.model.UserResponse
import retrofit2.http.GET
import rx.Observable

interface UserAPI {
    @GET("?results=10")
    fun getUser(): Observable<UserResponse>
}