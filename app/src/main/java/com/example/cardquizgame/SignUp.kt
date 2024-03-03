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
import java.text.SimpleDateFormat


class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        val btnsignup : Button = findViewById<Button>(R.id.btn2_signup)


        val  txtpassword       : EditText =  findViewById<EditText>(R.id.txtsignup_password)
        val  txtpasswordlayout : TextInputLayout = findViewById<TextInputLayout>(R.id.txtsignup_Input_password)
        val  txtemail          : EditText =  findViewById<EditText>(R.id.txtsignup_email)
        val  txtemailLayout    : TextInputLayout =  findViewById<TextInputLayout>(R.id.txtsignup_Input_email)

        val   txtfn            : EditText =  findViewById<EditText>(R.id.txtsignup_firstname)
        val   txtfnLayout      : TextInputLayout = findViewById<TextInputLayout>(R.id.txtsignup_Input_firstname)

        val    txtln           : EditText = findViewById<EditText>(R.id.txtsignup_lastname)
        val    txtlnLayout     : TextInputLayout =  findViewById<TextInputLayout>(R.id.txtsignup_Input_lastname)

        val    txtdob          : EditText = findViewById<EditText>(R.id.txtsignup_date)
        val    txtdobLayout    : TextInputLayout = findViewById<TextInputLayout>(R.id.txtsignup_Input_date)


        val signinlink           : TextView = findViewById<TextView>(R.id.link_signin);


        signinlink.setOnClickListener {
            val intentSignIn : Intent = Intent(this@SignUp,LoginActivity::class.java)
            startActivity(intentSignIn)
        }

        btnsignup.setOnClickListener()
        {

            //*********************************
            // validate first name
            //   1.- Required
            //   2.- 3<=length <= 30
            //*********************************

            val firstname   = txtfn.text.toString().trim();

            if (firstname.isEmpty())
            {
                txtfnLayout.error ="First name is required"
                return@setOnClickListener
            }
            else
                txtfnLayout.error=null;

            if ((firstname.length<3)  or (firstname.length>30) )
            {
                txtfnLayout.error ="First name must be at least 3 characters( no more 30)"
                return@setOnClickListener
            }
            else
            {
                txtfnLayout.error=null
            }

            //*********************************
            // validate last name
            //   1.- Required
            //   2.- 3<=length <= 30
            //*********************************

            val lastname   = txtln.text.toString().trim();

            if (lastname.isEmpty())
            {
                txtlnLayout.error ="Last name is required"
                return@setOnClickListener
            }
            else
                txtlnLayout.error=null;

            if ((lastname.length<3)  or (lastname.length>30) )
            {
                txtlnLayout.error ="Last name must be at least 3 characters( no more 30)"
                return@setOnClickListener
            }
            else
            {
                txtlnLayout.error=null
            }

            //*********************************
            // validate DOB
            //   1.- Required
            //   2.-Valid Date Format
            //*********************************

            val dob = txtdob.text.toString().trim();

            if (dob.isEmpty())
            {
                txtdobLayout.error="Date of Birth is required"
                return@setOnClickListener
            }
            else
            {
                txtdobLayout.error=null;
            }


            val format= SimpleDateFormat("dd/mm/yyyy")
            format.isLenient=false;

            try {
                format.parse(dob);
                txtdobLayout.error=null;
            } catch (e:Exception)
            {
                txtdobLayout.error=" Invalid date format (Format:dd/mm/yyyy)"
                return@setOnClickListener
            }




            //*********************************
            // validate email
            //   1.- required
            //   2.- valid email
            //*********************************
            val email    = txtemail.text.toString().trim();

            if (email.isEmpty())
            {
                txtemailLayout.error="Email is required"
                return@setOnClickListener
            }
            else
                txtemailLayout.error=null;

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                txtemailLayout.error= "Enter valid email"
                return@setOnClickListener
            }
            else
                txtemailLayout.error=null;


            //***************************
            // validate password
            //***************************
            val password    = txtpassword.text.toString().trim();

            if (password.isEmpty())
            {
                txtpasswordlayout.error="Password is required"
                return@setOnClickListener
            }
            else
                txtpasswordlayout.error=null;




            val jsonstring : String = GetJsonUserData(txtemail,txtpassword)


            val sharedPreferences =getSharedPreferences(APP_USERS, Context.MODE_PRIVATE)

            if (sharedPreferences != null) {
                with(sharedPreferences.edit()) {
                    putString(com.example.cardquizgame.APP_USERS, jsonstring)
                    apply()
                }
            }
            val intentsignin : Intent = Intent(this@SignUp,LoginActivity::class.java)

            startActivity(intentsignin)
        }



    }

    fun GetJsonUserData(txtemail:EditText,txtpassword:EditText) : String
    {

        val sharedPreferences4 = getSharedPreferences(APP_USERS, Context.MODE_PRIVATE)
        val data_users = sharedPreferences4.getString(APP_USERS,"").toString()
//
        var JSON_STRING : String =""
        try {
            val obj: JSONObject = JSONObject(data_users)

            val userArray = obj.getJSONArray("users")

            val jsonobj :JSONObject = JSONObject();
            jsonobj.put("email",txtemail.text.toString().trim())
            jsonobj.put("password",txtpassword.text.toString().trim())

            userArray.put(jsonobj)

            JSON_STRING =  obj.toString();

        } catch (e: JSONException) {

            val jsonobj: JSONObject = JSONObject();
            jsonobj.put("email", txtemail.text.toString().trim())
            jsonobj.put("password", txtpassword.text.toString().trim())

            val jsonarry: JSONArray = JSONArray();

            jsonarry.put(jsonobj)

            val jsonusers: JSONObject = JSONObject();
            jsonusers.put("users", jsonarry)

            JSON_STRING = jsonusers.toString()
        }

       return JSON_STRING;
    }
}