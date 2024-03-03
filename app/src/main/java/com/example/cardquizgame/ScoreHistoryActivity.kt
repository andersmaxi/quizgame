package com.example.cardquizgame


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject


class ScoreHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_history)


        val btnhome: Button = findViewById<Button>(R.id.btnhome_his)


        val curr_activity = intent

        val useremail = curr_activity.getStringExtra("user_email")

       try {
           var arrayAdapter: ArrayAdapter<*>

           val listOfStrings = mutableListOf<String>()

           val sharedPreferences4 = getSharedPreferences(APP_USERS_QUESTION, Context.MODE_PRIVATE)
           val JSON_STRING = sharedPreferences4.getString(APP_USERS_QUESTION,"NO VALUE")


           val obj: JSONObject = JSONObject(JSON_STRING)
           // fetch JSONArray named users
           val  userArray : JSONArray = obj.getJSONArray("history");

           //val  len =  userArray.length()
           var i = 0;
           while (i < userArray.length())
           {

               val userDetail = userArray.getJSONObject(i)
               //val user  = userDetail.getJSONObject("users")
               // get email
               val store_email = userDetail.getString("email")
               val score       = userDetail.getString("score")
               val date        = userDetail.getString("date")


               if (useremail.toString() == store_email.toString())  {
                       val line :String =  "Score: "+score +"   Date: "+ date
                       listOfStrings.add(line)
               }
               i+=1;
           }


           val scores = listOfStrings.toTypedArray()

           // access the listView from xml file
           var listscores : ListView = findViewById<ListView>(R.id.listscores)
           arrayAdapter = ArrayAdapter(this,
              android.R.layout.simple_list_item_1, scores)


           listscores.adapter = arrayAdapter
       }
       catch ( e : Exception)
       {

       }

        //**************************************************
        //  Go Home Button
        //**************************************************
        btnhome.setOnClickListener()
        {
            val intenthome : Intent = Intent(this@ScoreHistoryActivity,HomeActivity::class.java)

            val curr_activity = intent

            intenthome.putExtra("user_email",curr_activity.getStringExtra("user_email"))

            startActivity(intenthome)
        }
    }
}