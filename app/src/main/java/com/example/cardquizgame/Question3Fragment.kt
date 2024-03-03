package com.example.cardquizgame

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Question3Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Question3Fragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view : View  = inflater.inflate(R.layout.fragment_question3, container, false)

        val radioq : RadioGroup = view.findViewById<RadioGroup>(R.id.rg_question3)

        radioq.setOnCheckedChangeListener(){
            radioGroup: RadioGroup, i: Int ->

            val radioButton : RadioButton = view.findViewById<RadioButton>(i)

            var radiocheck: String = "";

            Global.AnswerQuestion[2]=true;


            radiocheck= radioButton.text.toString().substring(0,1)
            Global.UserResponses[2]=radiocheck

            val sharedPreferences =
                activity?.getSharedPreferences(APP_QUESTION_3, Context.MODE_PRIVATE)

            if (sharedPreferences != null) {
                with(sharedPreferences.edit()) {
                    putString(APP_QUESTION_3, radiocheck)
                    apply()
                }
            }

        }

        return view
    }


}