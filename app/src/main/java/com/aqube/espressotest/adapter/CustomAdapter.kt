package com.aqube.espressotest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aqube.espressotest.R
import com.aqube.espressotest.model.User
import kotlin.coroutines.coroutineContext

class CustomAdapter(val userList: ArrayList<User>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //This method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    //This method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])
        // Item click listener
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, userList.get(position).name, Toast.LENGTH_LONG).show()
        }
    }

    //This method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //The class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: User) {
            val textViewName = itemView.findViewById(R.id.textViewUsername) as TextView
            val textViewAddress = itemView.findViewById(R.id.textViewAddress) as TextView
            textViewName.text = user.name
            textViewAddress.text = user.address
        }
    }
}