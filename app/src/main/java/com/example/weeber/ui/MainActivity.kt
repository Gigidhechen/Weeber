package com.example.weeber.ui

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weeber.R
import com.example.weeber.data.model.User

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
        presenter?.generateItemsData()
    }

    override fun showItemsData(items: ArrayList<User>) {
        val timeList: RecyclerView? = findViewById(R.id.recyclerview_worker)

        val recyclerAdapter = RecyclerAdapter(items)

        if (timeList != null) {
            timeList.layoutManager = LinearLayoutManager(this@MainActivity)
            timeList.adapter = recyclerAdapter
        }
    }
}