package com.example.cardquizgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txtemail : EditText = findViewById<EditText>(R.id.txtemail)
        val txtpassword : EditText = findViewById<EditText>(R.id.txtpassword)
        val txtemaillayout : TextInputLayout = findViewById<TextInputLayout>(R.id.txtInputEmail)
        val txtpasswordlayout : TextInputLayout = findViewById<TextInputLayout>(R.id.txtInputPassword)

        val signuplink           : TextView = findViewById<TextView>(R.id.link_signup);

        signuplink.setOnClickListener {
            //*************************************************
            // when user clicks link, go to Sign UP Screen
            //*************************************************
            val intentSignUp : Intent = Intent(this@LoginActivity,SignUp::class.java)
            startActivity(intentSignUp)
        }



        val btnsignin : Button = findViewById<Button>(R.id.btnsignin)

        btnsignin.setOnClickListener()
        {


            //***************************
            // validate Email
            //  1.- Required
            //  2.- Valid email
            //***************************
            val email =  txtemail.text.toString().trim();

            if (email.isEmpty())
            {
                txtemaillayout.error = "Email is required"
                return@setOnClickListener
            }
            else
                txtemaillayout.error=null;

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                txtemaillayout.error = "Enter valid email"
                return@setOnClickListener
            }
            else
                txtemaillayout.error=null;

            //***************************
            // validate Password
            //***************************

            val  password = txtpassword.text.toString().trim();

            if (password.isEmpty())
            {
                txtpasswordlayout.error = "Password is required"
                return@setOnClickListener
            }

            if (!validateUserPassword(email,password))
            {
                txtpasswordlayout.error = "Invalid User or Password"
                return@setOnClickListener
            }


           val intentquiz : Intent = Intent (this@LoginActivity,HomeActivity::class.java)

            intentquiz.putExtra("user_email",email)
            startActivity(intentquiz)
        }


    }

    fun validateUserPassword(email:String , pass:String) : Boolean
    {


        val sharedPreferences4 = getSharedPreferences(APP_USERS, Context.MODE_PRIVATE)
        val JSON_STRING = sharedPreferences4.getString(APP_USERS,"NO VALUE")

        try {

            val obj: JSONObject = JSONObject(JSON_STRING)
            // fetch JSONArray named users
            val  userArray :JSONArray = obj.getJSONArray("users");

            //val  len =  userArray.length()
            var i = 0;
            while (i < userArray.length())
            {

                val userDetail = userArray.getJSONObject(i)
                //val user  = userDetail.getJSONObject("users")
                // get email
                val store_email = userDetail.getString("email")
                val store_pass  = userDetail.getString("password")


                if ((email.toString() == store_email.toString()) and (pass.toString() == store_pass.toString())) {
                    return true
                }
               i+=1;
            }
           return false;

        } catch (e: JSONException) {
            e.printStackTrace()
            return false
        }

    }
}