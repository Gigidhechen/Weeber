package com.example.weeber.ui.LoginActivity

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.example.weeber.R
import com.google.firebase.auth.FirebaseAuth

class LoginPresenter(
    private val view:LoginContract.View,
    private val context: Context
): LoginContract.Presenter {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun signIn (email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    view.onLoginSuccess()
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    view.onLoginFailure(context.getString(R.string.loginFailure))
                }
            }

    }

    override fun RegisterUser (email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    view.onRegistrationSuccess()
                    signIn(email, password)
                } else {
                    view.onRegistrationFailure(context.getString(R.string.registrationFailure))
                }
            }
    }
    override fun checkCurrentUser(){
        val currentUser = auth.currentUser
        if (currentUser != null) {
            view.onLoginSuccess()
        }
    }
}