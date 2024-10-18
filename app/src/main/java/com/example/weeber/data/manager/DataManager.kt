package com.example.weeber.data.manager

import android.content.Context
import com.example.appclima.util.SharedPreferencesConnector
import com.example.weeber.data.model.UserResponse
import com.example.weeber.data.remote.api.UserAPI
import com.example.weeber.data.remote.client.ServiceGenerator
import rx.Observable

class DataManager(val context: Context) {
    private val connector = SharedPreferencesConnector.getInstance(context)
    fun getUser() : Observable<UserResponse>{
        return ServiceGenerator.createService(UserAPI::class.java, context).getUser()
    }
}