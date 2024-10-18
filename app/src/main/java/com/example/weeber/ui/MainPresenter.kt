package com.example.weeber.ui

import android.content.Context
import com.example.weeber.data.manager.DataManager
import com.example.weeber.data.model.UserResponse
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
    }


