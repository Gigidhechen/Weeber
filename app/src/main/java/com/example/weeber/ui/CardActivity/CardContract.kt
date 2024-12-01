package com.example.weeber.ui.CardActivity

import com.example.weeber.data.model.Num


interface CardContract {
    interface View{
        fun showInformation()
        fun sendEmail()
    }

    interface  Presenter{

    }
}