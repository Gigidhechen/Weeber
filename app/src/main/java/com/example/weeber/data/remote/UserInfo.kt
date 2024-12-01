package com.example.weeber.data.remote

import com.example.weeber.data.model.User

object UserInfo {
    object user {
        var user: User = User(null,null,null,null,null,null)
        var review = ArrayList<Int>()
        var distance = ArrayList<Int>()
        var Price = ArrayList<Int>()
    }

    object currentPos{
        var pos = 1
    }

    object precio {
        var precio = 0
    }

}