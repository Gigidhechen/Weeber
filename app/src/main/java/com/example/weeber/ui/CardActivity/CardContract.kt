package com.example.weeber.ui.CardActivity

import com.example.weeber.data.model.Num


interface CardContract {
    interface View{
        fun showPrice(item: ArrayList<Num>)
        fun showDesc(item: String)
    }

    interface  Presenter{
        fun generatePrice()
        fun generateDesc()
    }
}