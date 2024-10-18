package com.example.weeber.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weeber.R
import com.example.weeber.data.model.User

class RecyclerAdapter (val items : ArrayList<User>?) : RecyclerView.Adapter<RecyclerAdapter.TimesViewHolder>() {
    private var onContractClickListener : OnContractClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.TimesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.worker_item, parent, false)
        return TimesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.TimesViewHolder, position: Int) {
        val item = items?.get(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onContractClickListener?.onClick(position,item!!)
        }
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    fun setOnClickListener(onContractClickListener: OnContractClickListener) {
        this.onContractClickListener = onContractClickListener
    }


    class TimesViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.worker_name)
        val gender = view.findViewById<TextView>(R.id.gender)
        val review = view.findViewById<TextView>(R.id.review)
        val distance = view.findViewById<TextView>(R.id.distance)
        val image = view.findViewById<ImageView>(R.id.worker_image)



        fun bind(info : User?) {

            if(info != null) {
                var imageUser : Bitmap? = null
                val `in` = java.net.URL(info.picture?.large.toString()).openStream()
                imageUser = BitmapFactory.decodeStream(`in`)
                name.text = info.name?.first.toString() + " " + info.name?.last.toString()
                gender.text = info.gender
                image.setImageBitmap(imageUser)
            }
        }
    }

    interface OnContractClickListener {
        fun onClick(position: Int, item: User)
    }


}