package com.quang.vpbank.ai.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quang.vpbank.ai.R
import com.quang.vpbank.ai.adapter.MessageAdapter
import com.quang.vpbank.ai.model.Message
import com.quang.vpbank.ai.utils.ApiUtils
import kotlinx.android.synthetic.main.fragment_alice.*
import kotlinx.android.synthetic.main.fragment_quiz_content.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 *
 */
class AliceFragment : Fragment() {

    var isRandomFinished = false
    var id = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMessage.layoutManager = LinearLayoutManager(view.context)
        val listMessage = ArrayList<Message>()
        val adapter = MessageAdapter(listMessage)
        rvMessage.adapter = adapter

        ApiUtils.getQuestionService().randomQuestion.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if (response!!.isSuccessful) {
                    val data = JSONObject(response.body()).getJSONArray("data").getJSONObject(0)
                    id = data.getString("_id")
                    val question = data.getString("content")
                    val options = data.getJSONArray("options")
                    val answer1 = options.getJSONObject(0).getString("value")
                    val answer2 = options.getJSONObject(1).getString("value")
                    val answer3 = options.getJSONObject(2).getString("value")
                    val answer4 = options.getJSONObject(3).getString("value")
                    listMessage.add(Message("$question\n\nA. $answer1\nB. $answer2\nC. $answer3\nD. $answer4", true))
                    adapter.notifyDataSetChanged()
                }
            }

        })

        imvSend.setOnClickListener {
            val message = edtMessage.text.toString()
            if (message.trim().isNotEmpty()) {
                listMessage.add(Message(message, false))
                adapter.notifyDataSetChanged()
                edtMessage.setText("")
                rvMessage.scrollToPosition(listMessage.size - 1)

                if (!isRandomFinished) {
                    val answer = if (message.trim() == "A") 0 else if (message.trim() == "B") 1 else if (message.trim() == "C") 3 else 4
                    ApiUtils.getQuestionService().sendAnswer(id, answer).enqueue(object : Callback<String> {
                        override fun onFailure(call: Call<String>?, t: Throwable?) {
                            Log.e("Answer", t.toString())
                        }

                        override fun onResponse(call: Call<String>?, response: Response<String>?) {
                            if (progressBar != null) progressBar.visibility = View.INVISIBLE
                            if (response!!.isSuccessful && JSONObject(response.body()).getBoolean("data")) {
                                val description = JSONObject(response.body()).getString("message")
                                listMessage.add(Message("Right!\n\n$description", true))
                            } else {
                                val description = JSONObject(response.body()).getString("message")
                                listMessage.add(Message("Wrong!!!\n" +
                                        "\n" +
                                        "$description", true))
                            }
                            adapter.notifyDataSetChanged()
                            rvMessage.scrollToPosition(listMessage.size - 1)
                            isRandomFinished = true
                        }

                    })
                } else {
                    val body = JSONObject()
                    body.put("query", message)
                    body.put("lang", "en")
                    body.put("sessionId", "12345678")
                    ApiUtils.getAliceService().sendMessage("Bearer f5565462e958468eaa13fabe72bab270", body.toString())
                            .enqueue(object : Callback<String> {
                                override fun onFailure(call: Call<String>?, t: Throwable?) {
                                    Log.e("Chat bot", t.toString())
                                }

                                override fun onResponse(call: Call<String>?, response: Response<String>?) {
                                    if (response!!.isSuccessful) {
                                        try {
                                            Log.d("Chat bot", response.body())
                                            val res = JSONObject(response.body())
                                            val speech = res.getJSONObject("result").getString("speech")
                                            listMessage.add(Message(speech, true))
                                            adapter.notifyDataSetChanged()
                                            rvMessage.scrollToPosition(listMessage.size - 1)
                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                        }

                                    }
                                }
                            })
                }
            }
        }

    }

}
