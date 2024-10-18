package com.example.weeber.ui

import com.example.weeber.data.model.User

interface MainContract {

    interface View{
        fun showItemsData(items : ArrayList<User>)
    }

    interface  Presenter{
       fun generateItemsData()

    }
}