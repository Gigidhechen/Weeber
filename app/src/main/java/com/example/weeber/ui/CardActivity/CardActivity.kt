package com.example.weeber.ui.CardActivity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weeber.R
import com.example.weeber.data.model.Num
import com.example.weeber.data.model.User
import com.example.weeber.data.remote.UserInfo
import com.example.weeber.ui.MainPresenter
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text

class CardActivity : AppCompatActivity(), CardContract.View {
    private var presenter: CardPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)


        presenter = CardPresenter(this,this)

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
        val `in` = java.net.URL(UserInfo.user.user.picture?.large.toString()).openStream()
        imageUser = BitmapFactory.decodeStream(`in`)

        email.text = UserInfo.user.user.email
        phone.text = UserInfo.user.user.cell
        imagen.setImageBitmap(imageUser)
        name.text = UserInfo.user.user.name?.first + " " + UserInfo.user.user.name?.last
        precio.text = UserInfo.precio.precio.toString() + "$"
    }

    override fun sendEmail() {
        val body = findViewById<TextInputEditText>(R.id.textInputContacto)



        val emailBody = body.text.toString()

        val intent = Intent(Intent.ACTION_SEND)

        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("gigidhe@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Weeber")
        intent.putExtra(Intent.EXTRA_TEXT, emailBody)

        intent.type = "message/rfc822"
        startActivity(Intent.createChooser(intent, "Choose an Email client :"))

}



}