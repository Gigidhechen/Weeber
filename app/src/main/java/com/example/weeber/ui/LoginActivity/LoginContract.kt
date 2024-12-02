package com.example.weeber.ui.LoginActivity

import com.example.weeber.data.model.User

interface LoginContract {
    interface View{
        fun onLoginSuccess()
        fun onLoginFailure(message: String)
        fun onRegistrationSuccess()
        fun onRegistrationFailure(message: String)
    }

    interface  Presenter{
        fun signIn (email: String, password: String)
        fun RegisterUser (email: String, password: String)
        fun checkCurrentUser()
    }
}