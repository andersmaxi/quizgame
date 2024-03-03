package com.example.cardquizgame

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Question5Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Question5Fragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val view : View =  inflater.inflate(R.layout.fragment_question5, container, false)

        //val radioq : RadioGroup = view.findViewById<RadioGroup>(R.id.rg_question5)

        val chekboxa :CheckBox =  view.findViewById<CheckBox>(R.id.q5_A)
        val chekboxb :CheckBox =  view.findViewById<CheckBox>(R.id.q5_B)
        val chekboxc :CheckBox =  view.findViewById<CheckBox>(R.id.q5_C)
        val chekboxd :CheckBox =  view.findViewById<CheckBox>(R.id.q5_D)

        val listofletter:MutableList<String>  = mutableListOf();

        var radiocheck: String = "";

        //**************************************************
        //  CHECKBOX - Response A
        //**************************************************

        chekboxa.setOnClickListener()
        {
            if (chekboxa.isChecked) {
                Global.AnswerQuestion[4] = true;
                listofletter.add(chekboxa.text.toString().substring(0,1))
                radiocheck=liststring(listofletter)
            }
            else {
                listofletter.remove(chekboxa.text.toString().substring(0, 1))
                radiocheck=liststring(listofletter)
                if (radiocheck=="")
                  Global.AnswerQuestion[4] = false;

            }

            Global.UserResponses[4]=radiocheck


        }

        //**************************************************
        //  CHECKBOX - Response B
        //**************************************************

        chekboxb.setOnClickListener()
        {
            if (chekboxb.isChecked) {
                Global.AnswerQuestion[4] = true;
                listofletter.add(chekboxb.text.toString().substring(0,1))
                radiocheck=liststring(listofletter)
            }
            else {
                listofletter.remove(chekboxb.text.toString().substring(0, 1))
                radiocheck=liststring(listofletter)
                if (radiocheck=="")
                    Global.AnswerQuestion[4] = false;

            }

            Global.UserResponses[4]=radiocheck


        }
        //**************************************************
        //  CHECKBOX - Response C
        //**************************************************
        chekboxc.setOnClickListener()
        {
            if (chekboxc.isChecked) {
                Global.AnswerQuestion[4] = true;
                listofletter.add(chekboxc.text.toString().substring(0,1))
                radiocheck=liststring(listofletter)
            }
            else {
                listofletter.remove(chekboxc.text.toString().substring(0, 1))
                radiocheck=liststring(listofletter)
                if (radiocheck=="")
                    Global.AnswerQuestion[4] = false;

            }

            Global.UserResponses[4]=radiocheck


        }

        //**************************************************
        //  CHECKBOX - Response D
        //**************************************************
        chekboxd.setOnClickListener()
        {
            if (chekboxd.isChecked) {
                Global.AnswerQuestion[4] = true;
                listofletter.add(chekboxd.text.toString().substring(0,1))
                radiocheck=liststring(listofletter)
            }
            else {
                listofletter.remove(chekboxd.text.toString().substring(0, 1))
                radiocheck=liststring(listofletter)
                if (radiocheck=="")
                    Global.AnswerQuestion[4] = false;

            }

            Global.UserResponses[4]=radiocheck


        }



        return view
    }

    fun liststring(t :List<String>):String
    {
        val sortedNumbers = t.sortedBy {  it.last() }

        var answers:String= ""
        for (i in sortedNumbers)
        {
            answers+=i.toString()
        }

        return answers
    }


}