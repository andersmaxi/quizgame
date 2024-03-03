package com.example.cardquizgame


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View.INVISIBLE
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class QuizGameActivity<fragment1> : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_game)

        var GCurrentQuestion : Int = 0 ;

        Global.ConfirmQuestion[0]=false;
        Global.ConfirmQuestion[1]=false;
        Global.ConfirmQuestion[2]=false;
        Global.ConfirmQuestion[3]=false;
        Global.ConfirmQuestion[4]=false;

        Global.AnswerQuestion[0]= false;
        Global.AnswerQuestion[1]= false;
        Global.AnswerQuestion[2]= false;
        Global.AnswerQuestion[3]= false;
        Global.AnswerQuestion[4]= false;




        val btn_next : Button = findViewById<Button>(R.id.btn_next)
        val txt_confirm : TextView = findViewById<TextView>(R.id.txt_confirm)

        val builder = AlertDialog.Builder(this)


        builder.setTitle("Confirm Answer")
        builder.setMessage("Do you want to confirm your answer?")

        val fragment : FragmentContainerView = findViewById<FragmentContainerView>(R.id.fragmentquiz)



        val positiveButton = builder.setPositiveButton(android.R.string.yes) { dialog, which ->

            Global.ConfirmQuestion[GCurrentQuestion] = true
            btn_next.text = "Next"
            val lsletter : String = Global.UserResponses[GCurrentQuestion].toString().trim()
            if (Global.QuestionAnswer[GCurrentQuestion].toString()
                    .trim() == Global.UserResponses[GCurrentQuestion].toString().trim()
            ) {
                txt_confirm.text = buildString {
                    append(lsletter)
                    append(". ")
                    append("Correct Answer")
                }
                txt_confirm.setTextColor(Color.parseColor("#0F9D58"))
            } else {
                txt_confirm.text = buildString {
                    append(lsletter)
                    append(". ")
                    append("Wrong Answer")
                }
                txt_confirm.setTextColor(Color.parseColor("#FF0000"))
            }


        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            btn_next.text="Confirm Answer"


        }


        supportFragmentManager.commit{

            setReorderingAllowed(true)
            replace<Question1Fragment>(R.id.fragmentquiz)


        }


        btn_next.setOnClickListener()
        {


            if (!Global.AnswerQuestion[GCurrentQuestion])
            {
                Toast.makeText(this@QuizGameActivity,"The answer to this question must be selected.",Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }



            if (!Global.ConfirmQuestion[GCurrentQuestion])
            {

                builder.show()
                return@setOnClickListener
            }




            GCurrentQuestion+=1;

            btn_next.text="Confirm Answer"
            txt_confirm.text = ""


            if ( GCurrentQuestion==1)
            {
                supportFragmentManager.commit{

                    setReorderingAllowed(true)
                    replace<Question2Fragment>(R.id.fragmentquiz)

                }
            }

            if ( GCurrentQuestion==2)
            {
                supportFragmentManager.commit{

                    setReorderingAllowed(true)
                    replace<Question3Fragment>(R.id.fragmentquiz)

                }

            }

            if ( GCurrentQuestion==3)
            {
                supportFragmentManager.commit{

                    setReorderingAllowed(true)
                    replace<Question4Fragment>(R.id.fragmentquiz)

                }

            }

            if ( GCurrentQuestion==4) {
                supportFragmentManager.commit {

                    setReorderingAllowed(true)
                    replace<Question5Fragment>(R.id.fragmentquiz)

                }
            }

            if ( GCurrentQuestion==5)
            {
               val intentscore : Intent = Intent(this@QuizGameActivity,ScoreActivity::class.java)

                val curr_activity = intent

                intentscore.putExtra("user_email",curr_activity.getStringExtra("user_email"))

               startActivity(intentscore)

            }


            }

        }


    }









