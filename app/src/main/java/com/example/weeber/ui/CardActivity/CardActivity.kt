package com.example.weeber.ui.CardActivity

import android.os.Bundle
import android.os.StrictMode
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weeber.R
import com.example.weeber.data.model.User

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        findViewById<TextView>(R.id.descriptionContacto)


    }

}