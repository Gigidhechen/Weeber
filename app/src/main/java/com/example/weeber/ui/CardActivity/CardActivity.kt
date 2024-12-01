package com.example.weeber.ui.CardActivity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weeber.R
import com.example.weeber.data.remote.UserInformation
import com.example.weeber.ui.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.auth

class CardActivity : AppCompatActivity(), CardContract.View {
    private var presenter: CardPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)


        presenter = CardPresenter(this,this)
        val nameView = findViewById<TextView>(R.id.userName2)
        val user = Firebase.auth.currentUser
        user?.let {
            val name = it.email
            nameView.text = name.toString()
        }

        val btn = findViewById<Button>(R.id.homeButton).setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        showInformation()
        val button = findViewById<Button>(R.id.buttonSend)
        button.setOnClickListener(){
            sendEmail()
        }
    }

    override fun showInformation() {
        val email = findViewById<TextView>(R.id.correoContacto)
        val phone = findViewById<TextView>(R.id.telefonoContacto)
        val imagen = findViewById<ImageView>(R.id.imagenContacto)
        val name = findViewById<TextView>(R.id.nombreContacto)
        val precio = findViewById<TextView>(R.id.PrecioContacto)

        var imageUser: Bitmap? = null
        val `in` = java.net.URL(UserInformation.user.user.picture?.large.toString()).openStream()
        imageUser = BitmapFactory.decodeStream(`in`)

        email.text = UserInformation.user.user.email
        phone.text = UserInformation.user.user.cell
        imagen.setImageBitmap(imageUser)
        name.text = UserInformation.user.user.name?.first + " " + UserInformation.user.user.name?.last
        precio.text = UserInformation.precio.precio.toString() + "$"
    }

    override fun sendEmail() {
        val body = findViewById<TextInputEditText>(R.id.textInputContacto)

        val emailBody = body.text.toString()

        val intent = Intent(Intent.ACTION_SEND)

        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(UserInformation.user.user.email.toString(), "gigidhe@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Weeber")
        intent.putExtra(Intent.EXTRA_TEXT, emailBody)

        intent.type = "message/rfc822"
        startActivity(Intent.createChooser(intent, "Choose an Email client :"))

}



}