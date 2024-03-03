package com.example.cardquizgame

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [Question1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Question1Fragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val view : View =inflater.inflate(R.layout.fragment_question1, container, false)

        val radiog  : RadioGroup   = view.findViewById<RadioGroup>(R.id.rg_question1)



        radiog.setOnCheckedChangeListener()
        { radioGroup: RadioGroup, i: Int ->


            val radioButton : RadioButton = view.findViewById<RadioButton>(i)

            var radiocheck: String = "";




            Global.AnswerQuestion[0]=true;


            radiocheck= radioButton.text.toString().substring(0,1)
           Global.UserResponses[0]=radiocheck

            val sharedPreferences =
                activity?.getSharedPreferences(APP_QUESTION_1, Context.MODE_PRIVATE)

            if (sharedPreferences != null) {
                with(sharedPreferences.edit()) {
                    putString(APP_QUESTION_1, radiocheck)
                    apply()
                }
            }


        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity: Activity? = activity

        //activity.startActivity(QuizGameActivity)


    }
}