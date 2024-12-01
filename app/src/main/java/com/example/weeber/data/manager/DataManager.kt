package com.example.weeber.data.manager

import android.content.Context
import com.example.appclima.util.SharedPreferencesConnector
import com.example.weeber.data.model.NumResponse
import com.example.weeber.data.model.UserResponse
import com.example.weeber.data.remote.api.LoremAPI
import com.example.weeber.data.remote.api.NumberAPI
import com.example.weeber.data.remote.api.UserAPI
import com.example.weeber.data.remote.client.ServiceGenerator
import com.example.weeber.data.remote.client.ServiceGeneratorLorem
import com.example.weeber.data.remote.client.ServiceGeneratorNumber
import rx.Observable

class DataManager(val context: Context) {
    private val connector = SharedPreferencesConnector.getInstance(context)
    fun getUser() : Observable<UserResponse>{
        return ServiceGenerator.createService(UserAPI::class.java, context).getUser()
    }

     fun getDescription() : Observable<String>{
        return ServiceGeneratorLorem.createService(LoremAPI::class.java,context).getDescription()
    }

    fun getPrice() : Observable<ArrayList<Int>>{
        return ServiceGeneratorNumber.createService(NumberAPI::class.java,context).getRandomNumbers()
    }
}