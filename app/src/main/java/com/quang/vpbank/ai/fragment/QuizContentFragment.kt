package com.quang.vpbank.ai.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quang.vpbank.ai.R
import com.quang.vpbank.ai.utils.ApiUtils
import kotlinx.android.synthetic.main.fragment_quiz_content.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 *
 */
class QuizContentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments!!.getString("id")
        val question = arguments!!.getString("question")
        tvQuestion.text = question
        val answer1 = arguments!!.getString("answer1")
        btnAnswer1.text = answer1
        val answer2 = arguments!!.getString("answer2")
        btnAnswer2.text = answer2
        val answer3 = arguments!!.getString("answer3")
        btnAnswer3.text = answer3
        val answer4 = arguments!!.getString("answer4")
        btnAnswer4.text = answer4

        btnAnswer1.setOnClickListener {
            check(id, 0)
        }
        btnAnswer2.setOnClickListener {
            check(id, 1)
        }
        btnAnswer3.setOnClickListener {
            check(id, 2)
        }
        btnAnswer4.setOnClickListener {
            check(id, 3)
        }
    }

    private fun check(id: String, answer: Int) {
        if (progressBar != null) progressBar.visibility = View.VISIBLE
        ApiUtils.getQuestionService().sendAnswer(id, answer).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.e("Answer", t.toString())
            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if (progressBar != null) progressBar.visibility = View.INVISIBLE
                if (response!!.isSuccessful && JSONObject(response.body()).getBoolean("data")) {
                    val description = JSONObject(response.body()).getString("message")
                    showMessage("Right!\n\n$description", view!!)
                } else {
                    val description = JSONObject(response.body()).getString("message")
                    showMessage("Wrong!!!\n\n$description", view!!)
                }
            }

        })
    }

    private fun showMessage(message: String, view: View) {
        val dialog = AlertDialog.Builder(view.context)
        dialog.setMessage(message)
        dialog.create()
        if (!activity!!.isFinishing) dialog.show()
    }

}
