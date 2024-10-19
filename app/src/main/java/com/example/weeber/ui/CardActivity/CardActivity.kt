package com.example.weeber.ui.CardActivity

import android.os.Bundle
import android.os.StrictMode
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weeber.R
import com.example.weeber.data.model.Num
import com.example.weeber.data.model.User
import com.example.weeber.ui.MainPresenter

class CardActivity : AppCompatActivity(), CardContract.View {
    private var presenter: CardPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        findViewById<TextView>(R.id.descriptionContacto)

        presenter = CardPresenter(this,this)
        presenter?.generateDesc()
        presenter?.generatePrice()
    }

    override fun showPrice(item: ArrayList<Num>) {
        findViewById<TextView>(R.id.descriptionContacto).text = item.get(0).toString()
    }

    override fun showDesc(item: String) {
        findViewById<TextView>(R.id.textInputContacto).text = item
    }

}