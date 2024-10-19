package com.example.weeber.ui.CardActivity

import android.content.Context
import com.example.weeber.data.manager.DataManager
import com.example.weeber.data.model.NumResponse
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CardPresenter(
    private val view: CardContract.View,
    private val context: Context
) : CardContract.Presenter {

    private var dataManager = DataManager(context)

    override fun generatePrice() {
        val observable = dataManager.getPrice()
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<NumResponse>(){

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                }

                override fun onNext(t: NumResponse) {
                    view.showPrice(t.results!!)
                }

                override fun onCompleted() {
                }
            })

    }

    override fun generateDesc() {
        val observable = dataManager.getDescription()
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<String>(){

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                }

                override fun onNext(t: String) {
                    view.showDesc(t)
                }
                override fun onCompleted() {
                }
            })
    }
}