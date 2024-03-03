package com.example.cardquizgame

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


        val buttonLogin : Button = findViewById<Button>(R.id.btnlogin)
        val buttonSignup : Button = findViewById<Button>(R.id.btnsignup)

        val text4 :TextView = findViewById<TextView>(R.id.text4)

        val sharedPreferences4 = getSharedPreferences(APP_USERS, Context.MODE_PRIVATE)
        val defaultValue4 = sharedPreferences4.getString(APP_USERS,"NO VALUE")


        //**************************************************
        //  Login Button
        //**************************************************
          buttonLogin.setOnClickListener()
          {
              val intentlogin: Intent = Intent(this@WelcomeActivity,LoginActivity::class.java)

              startActivity(intentlogin)
          }
        //**************************************************
        //  Sign UP Button
        //**************************************************

        buttonSignup.setOnClickListener()
        {
            val intentsignup :Intent  = Intent(this@WelcomeActivity,SignUp::class.java)
            startActivity(intentsignup)
        }


    }
}