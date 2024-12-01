package com.example.weeber.ui

import android.content.Context
import com.example.weeber.data.manager.DataManager
import com.example.weeber.data.model.UserResponse
import com.example.weeber.data.remote.UserInfo
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.Console

class MainPresenter(
    private val view: MainContract.View,
    private val context: Context
) : MainContract.Presenter {

    private var dataManager = DataManager(context)

    override fun generateItemsData(){
        val observable = dataManager.getUser()
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<UserResponse>(){

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                }

                override fun onNext(t: UserResponse) {
                    view.showItemsData(t.results!!)
                }
                override fun onCompleted() {
                }
            })

    }

    override fun generatePrice() {
        val observable = dataManager.getPrice()
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<ArrayList<Int>>() {
                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                    System.out.print("no funciona")
                }
                override fun onNext(t: ArrayList<Int>) {
                    UserInfo.price.Price = t
                }
                override fun onCompleted() {
                }
            })
        }



}


