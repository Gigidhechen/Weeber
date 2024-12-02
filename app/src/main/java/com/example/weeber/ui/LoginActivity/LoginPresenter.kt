package com.example.weeber.ui.LoginActivity

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.weeber.ui.MainActivity
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
                    view.onLoginFailure("No se pudo hacer Login")
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
                    view.onRegistrationFailure("Fallo en el registro. Intentalo de nuevo")
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