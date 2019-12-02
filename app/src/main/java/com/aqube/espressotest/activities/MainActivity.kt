package com.aqube.espressotest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.aqube.espressotest.adapter.CustomAdapter
import com.aqube.espressotest.R
import com.aqube.espressotest.model.User


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindLoggedInUser()

        bindRecyclerView()
    }

    /**
     * Binding user email
     */
    private fun bindLoggedInUser() {
        val welcomeMessage = String.format("Hi %s!", intent.getStringExtra("email")!!)
        textViewWelcome.text = welcomeMessage
    }

    /**
     * Binding RecyclerView with dummy data
     */
    private fun bindRecyclerView() {
        //Adding a LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        //Crating an ArrayList to store users using the data class user
        val users: ArrayList<User> = getUserList()
        //creating our adapter
        val adapter = CustomAdapter(users)
        //Adding the adapter to RecyclerView
        recyclerView.adapter = adapter
    }

    /**
     * User dummy data
     */
    private fun getUserList(): ArrayList<User> {
        val users = ArrayList<User>()
        //Adding some dummy data to the list
        users.add(User("Belal Khan", "Ranchi Jharkhand"))
        users.add(User("Ramiz Khan", "Ranchi Jharkhand"))
        users.add(User("Faiz Khan", "Ranchi Jharkhand"))
        users.add(User("Yashar Khan", "Ranchi Jharkhand"))
        users.add(User("Belal Khan", "Ranchi Jharkhand"))
        users.add(User("Ramiz Khan", "Ranchi Jharkhand"))
        users.add(User("Faiz Khan", "Ranchi Jharkhand"))
        users.add(User("Yashar Khan", "Ranchi Jharkhand"))
        users.add(User("Belal Khan", "Ranchi Jharkhand"))
        users.add(User("Ramiz Khan", "Ranchi Jharkhand"))
        users.add(User("Faiz Khan", "Ranchi Jharkhand"))
        users.add(User("Yashar Khan", "Ranchi Jharkhand"))
        users.add(User("Belal Khan", "Ranchi Jharkhand"))
        users.add(User("Ramiz Khan", "Ranchi Jharkhand"))
        users.add(User("Faiz Khan", "Ranchi Jharkhand"))
        users.add(User("Yashar Khan", "Ranchi Jharkhand"))

        return users
    }
}
