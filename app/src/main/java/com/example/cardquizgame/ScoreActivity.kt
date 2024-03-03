package com.example.cardquizgame

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime


class ScoreActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val yourscore :TextView = findViewById<TextView>(R.id.yourscore)
        val yourscoredetail : TextView = findViewById<TextView>(R.id.yourscoredetail)

        val btnretest : Button  = findViewById<Button>(R.id.btn_retest)

        val btnhome : Button   = findViewById<Button>(R.id.btnhome)
        val text4     : TextView = findViewById<TextView>(R.id.text4)

        var JSON_STRING : String =""


        //**************************************************
        //  Button - Restest
        //**************************************************

        btnretest.setOnClickListener()
        {

            val intentquiz : Intent = Intent(this@ScoreActivity,MainActivity::class.java)

            val curr_activity = intent

            intentquiz.putExtra("user_email",curr_activity.getStringExtra("user_email"))

            startActivity(intentquiz)
        }

        //**************************************************
        //  Button - Go Home
        //**************************************************

        btnhome.setOnClickListener()
        {

            val intenthome : Intent = Intent(this@ScoreActivity,HomeActivity::class.java)

            val curr_activity = intent

            intenthome.putExtra("user_email",curr_activity.getStringExtra("user_email"))

            startActivity(intenthome)
        }



        val curr_activity = intent

        val useremail = curr_activity.getStringExtra("user_email")


        var score : Int = 0;
        var good  : Int =0;
        var index : Int =0;


        //**************************************************
        //  Get Total Score
        //**************************************************

        Global.QuestionAnswer.forEach {

            if (it.toString().trim()==Global.UserResponses[index].toString().trim())
            {
               score+=20;
                good+=1;
            }
            index+=1;
        }

        yourscore.text = score.toString()+ " %"

        yourscoredetail.text = good.toString()+ " out of 5 questions."

        val sharedPreferences4 = getSharedPreferences(APP_USERS_QUESTION, Context.MODE_PRIVATE)
        val history = sharedPreferences4.getString(APP_USERS_QUESTION,"").toString()

        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val lsdate = date.format(formatter)

        try {
            val obj: JSONObject = JSONObject(history)

            val userArray = obj.getJSONArray("history")

            val jsonscore : JSONObject = JSONObject();
            jsonscore.put("email",useremail.toString())
            jsonscore.put("score",score.toString())
            jsonscore.put("date",lsdate.toString())

            userArray.put(jsonscore)

            JSON_STRING =  obj.toString();



        } catch (e: JSONException) {
            val jsonscore : JSONObject = JSONObject();
            jsonscore.put("email",useremail.toString())
            jsonscore.put("score",score.toString())
            jsonscore.put("date",lsdate.toString())

            val jsonscorearry : JSONArray = JSONArray();

            jsonscorearry.put(jsonscore)

            val jsonhistory : JSONObject = JSONObject();
            jsonhistory.put("history",jsonscorearry)

            JSON_STRING =  jsonhistory.toString();


        }

        val sharedPreferences =getSharedPreferences(APP_USERS_QUESTION, Context.MODE_PRIVATE)

        if (sharedPreferences != null) {
            with(sharedPreferences.edit()) {
                putString(com.example.cardquizgame.APP_USERS_QUESTION, JSON_STRING)
                apply()
            }
        }

        val sharedPreferences6 = getSharedPreferences(APP_USERS_QUESTION, Context.MODE_PRIVATE)
        val history2 = sharedPreferences6.getString(APP_USERS_QUESTION,"NO VALUE").toString()

        //text4.text = history2;



    }
}