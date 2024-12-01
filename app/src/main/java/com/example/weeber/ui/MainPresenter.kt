package com.example.weeber.ui

import android.content.Context
import com.example.weeber.data.manager.DataManager
import com.example.weeber.data.model.UserResponse
import com.example.weeber.data.remote.UserInformation
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

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
                }
                override fun onNext(t: ArrayList<Int>) {
                    UserInformation.user.Price = t
                }
                override fun onCompleted() {
                }
            })
        }

    override fun generateRating() {
        val observable = dataManager.getReview()
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<ArrayList<Int>>() {
                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                }
                override fun onNext(t: ArrayList<Int>) {
                    UserInformation.user.review = t
                }
                override fun onCompleted() {
                }
            })
    }

    override fun generateDistance() {
        val observable = dataManager.getDistance()
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<ArrayList<Int>>() {
                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                }

                override fun onNext(t: ArrayList<Int>) {
                    UserInformation.user.distance = t
                }

                override fun onCompleted() {

                }
            })
    }


}


