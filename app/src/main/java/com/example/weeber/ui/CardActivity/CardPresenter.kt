package com.example.weeber.ui.CardActivity

import android.content.Context
import com.example.weeber.data.manager.DataManager
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CardPresenter(
    private val view: CardContract.View,
    private val context: Context
) : CardContract.Presenter {

    private var dataManager = DataManager(context)


}