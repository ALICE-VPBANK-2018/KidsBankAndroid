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
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 *
 */
class AliceFragment : Fragment() {

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

        imvSend.setOnClickListener {
            val message = edtMessage.text.toString()
            if (message.trim().isNotEmpty()) {
                listMessage.add(Message(message, false))
                adapter.notifyDataSetChanged()
                edtMessage.setText("")
                rvMessage.scrollToPosition(listMessage.size - 1)
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
