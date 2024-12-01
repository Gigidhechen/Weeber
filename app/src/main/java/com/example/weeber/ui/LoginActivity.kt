package com.example.weeber.ui

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
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_activity)
        auth = FirebaseAuth.getInstance()
        val emailEditText = findViewById<EditText>(R.id.LoginEmail)
        val passwordEditText = findViewById<EditText>(R.id.LoginPassword)
        val buttonLogin = findViewById<Button>(R.id.LogIn)
        val buttonsignIn = findViewById<Button>(R.id.SignIn)

        buttonLogin.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "El correo y la contraseña son necesarios", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            signIn(email, password)
        }

        buttonsignIn.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "El correo y la contraseña son necesarios", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "La contraseña tiene que ser de 6 digitos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            RegisterUser(email, password)
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null){

        }
    }

    private fun signIn (email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "No se pudo hacer Login", Toast.LENGTH_LONG).show()
                }
            }

    }

    private fun RegisterUser (email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Registro exitoso!", Toast.LENGTH_LONG).show()
                    signIn(email, password)
                } else {
                    Toast.makeText(this, "Fallo en el registro. Intentalo de nuevo", Toast.LENGTH_LONG).show()
                }
            }
    }
}