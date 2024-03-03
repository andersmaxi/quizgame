package com.example.cardquizgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

const val APP_QUESTION_1 : String = "USER_Response1"
const val APP_QUESTION_2 : String = "USER_Response2"
const val APP_QUESTION_3 : String = "USER_Response3"
const val APP_QUESTION_4 : String = "USER_Response4"
const val APP_QUESTION_5 : String = "USER_Response5"

const val APP_USERS          : String = "DB_USER"                    //  DATASTORE - Users
const val APP_USERS_QUESTION : String = "DB_USER_RESPONSE"           //  DATASTORE - Users Responses


//*************************************************************************
//   Sharepreferences used for SAVE DATA in JSON FORMAT
//    Example of values
//   DB_USER =- { users ,[{email:andre@hotmail.com,password:123 },{email:test@hotmail.com,password:12}]}
//   DB_USER_RESPONSE ={history,[email:andre@hotmal.com,score:30,date:2024-03-03}]}
//*************************************************************************


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startgame : Button = findViewById<Button>(R.id.startgame);
        val btngohome  : Button  = findViewById<Button>(R.id.btngohome)


        startgame.setOnClickListener()
        {
             val intentquiz  : Intent = Intent(this@MainActivity,QuizGameActivity::class.java)

            val curr_activity = intent

            intentquiz.putExtra("user_email",curr_activity.getStringExtra("user_email"))
            startActivity(intentquiz)

        }

        btngohome.setOnClickListener()
        {
            val intenthome  : Intent = Intent(this@MainActivity,HomeActivity::class.java)

            val curr_activity = intent

            intenthome.putExtra("user_email",curr_activity.getStringExtra("user_email"))
            startActivity(intenthome)

        }



    }
}