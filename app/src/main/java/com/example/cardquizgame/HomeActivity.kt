package com.example.cardquizgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //val startquiz: Button = findViewById<Button>(R.id.btntakequiz);
        val cardquiz :CardView = findViewById<CardView>(R.id.cardtakequiz)
        //val scorehistory: Button = findViewById<Button>(R.id.btnscorehistory)
        val cardhistory : CardView = findViewById<CardView>(R.id.cardhistory)
        //val logout      : Button = findViewById<Button>(R.id.btnlogout)
        val cardlogout  : CardView = findViewById<CardView>(R.id.cardlogout)


        //**************************************************
        //  TAKE Quiz Button
        //**************************************************

        cardquiz.setOnClickListener()
        {
            val intentquiz  : Intent = Intent(this@HomeActivity,MainActivity::class.java)

            val curr_activity = intent

            intentquiz.putExtra("user_email",curr_activity.getStringExtra("user_email"))
            startActivity(intentquiz)

        }

        //**************************************************
        //  Score History Button
        //**************************************************
        cardhistory.setOnClickListener()
        {
            val intenthistory  : Intent = Intent(this@HomeActivity,ScoreHistoryActivity::class.java)

            val curr_activity = intent

            intenthistory.putExtra("user_email",curr_activity.getStringExtra("user_email"))
            startActivity(intenthistory)

        }

        //**************************************************
        //  Logout Button
        //**************************************************

        cardlogout.setOnClickListener()
        {
            val intentlogout  : Intent = Intent(this@HomeActivity,LoginActivity::class.java)

            startActivity(intentlogout)

        }
    }
}