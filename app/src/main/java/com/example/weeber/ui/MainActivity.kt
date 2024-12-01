package com.example.weeber.ui

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weeber.R
import com.example.weeber.data.model.User
import com.example.weeber.data.remote.UserInfo
import com.example.weeber.ui.CardActivity.CardActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity(), MainContract.View {
    private var presenter: MainPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        presenter = MainPresenter(this,this)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        presenter?.generatePrice()
        presenter?.generateRating()
        presenter?.generateDistance()
        presenter?.generateItemsData()
        val nameView = findViewById<TextView>(R.id.userName)
        val user = Firebase.auth.currentUser
        user?.let {
            val name = it.email
            nameView.text = name.toString()
        }
    }


    override fun showItemsData(items: ArrayList<User>) {
        val timeList: RecyclerView? = findViewById(R.id.recyclerview_worker)

        val recyclerAdapter = RecyclerAdapter(items)

        if (timeList != null) {
            timeList.layoutManager = LinearLayoutManager(this@MainActivity)
            timeList.adapter = recyclerAdapter
        }

        recyclerAdapter.setOnClickListener(object : RecyclerAdapter.OnContractClickListener {
            override fun onClick(position: Int, user: User) {
                UserInfo.currentPos.pos=position
                UserInfo.user.user = user
                UserInfo.precio.precio = UserInfo.user.Price[position]
                val intent = Intent(this@MainActivity, CardActivity::class.java)
                startActivity(intent)
            }
        })
    }
}