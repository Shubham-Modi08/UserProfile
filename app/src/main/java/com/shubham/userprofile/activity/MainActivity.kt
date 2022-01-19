package com.shubham.userprofile.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.shubham.userprofile.R
import com.shubham.userprofile.model.Response
import com.shubham.userprofile.viewmodel.MainViewModel



class MainActivity : AppCompatActivity() {

    private lateinit var userData: Response
    private lateinit var viewModel: MainViewModel
    private lateinit var name :String
    private lateinit var phone :String
    private lateinit var email :String
    private lateinit var address :String
    private lateinit var dob :String
    private lateinit var age :String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = findViewById<TextView>(R.id.username)
        val userphone = findViewById<TextView>(R.id.userphone)
        val useremail = findViewById<TextView>(R.id.useremail)
        val useraddress = findViewById<TextView>(R.id.userAddress)
        val userdob = findViewById<TextView>(R.id.dob)
        val userage = findViewById<TextView>(R.id.age)


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.details.observe(this, {
            userData = it
            name = userData.results[0].name.first + " " + userData.results[0].name.last
            phone = userData.results[0].phone
            email = userData.results[0].email
            address = userData.results[0].location.street.name + "," + userData.results[0].location.city + "," + userData.results[0].location.state + "," + userData.results[0].location.country
            dob = userData.results[0].dob.date.substring(0,10)
            age = userData.results[0].dob.age.toString()

        })
        viewModel.fetchUserDetails()

        username.text = name
        userphone.text = phone
        useremail.text = email
        useraddress.text = address
        userdob.text = dob
        userage.text = age






    }
}