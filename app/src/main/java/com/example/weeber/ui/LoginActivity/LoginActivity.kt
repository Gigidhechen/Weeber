package com.example.weeber.ui.LoginActivity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.weeber.R
import com.example.weeber.ui.MainActivity
import com.example.weeber.ui.MainPresenter
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_activity)
        presenter = LoginPresenter(this,this)

        val emailEditText = findViewById<EditText>(R.id.LoginEmail)
        val passwordEditText = findViewById<EditText>(R.id.LoginPassword)
        val buttonLogin = findViewById<Button>(R.id.LogIn)
        val buttonsignIn = findViewById<Button>(R.id.SignIn)

        buttonLogin.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, getString(R.string.emailpwNeeded), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            presenter?.signIn(email, password)
        }

        buttonsignIn.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, getString(R.string.emailpwNeeded), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, getString(R.string.pwSixDigits), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            presenter?.RegisterUser(email, password)
        }
    }

    public override fun onStart() {
        super.onStart()
        presenter?.checkCurrentUser()
    }

    override fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onLoginFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onRegistrationSuccess() {
        Toast.makeText(this, getString(R.string.registrationSuccess), Toast.LENGTH_LONG).show()
    }

    override fun onRegistrationFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


}