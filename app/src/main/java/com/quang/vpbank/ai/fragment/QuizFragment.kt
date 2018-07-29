package com.quang.vpbank.ai.fragment


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quang.vpbank.ai.R
import com.quang.vpbank.ai.adapter.ViewPagerAdapter
import com.quang.vpbank.ai.utils.ApiUtils
import com.tmall.ultraviewpager.UltraViewPager
import kotlinx.android.synthetic.main.fragment_quiz.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 *
 */
class QuizFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL)

        val adapter = ViewPagerAdapter(childFragmentManager)

        ApiUtils.getQuestionService().getRandomQuestion(1).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if (response!!.isSuccessful) {
                    if (progressBar != null) progressBar.visibility = View.INVISIBLE
                    val data = JSONObject(response.body()).getJSONArray("data")
                    for (i in 0 until data.length()) {
                        val question = data.getJSONObject(i)
                        val id = question.getString("_id")
                        val content = question.getString("content")
                        val options = question.getJSONArray("options")
                        val a = QuizContentFragment()
                        val ques = Bundle()
                        ques.putString("id", id)
                        ques.putString("question", content)
                        ques.putString("answer1", options.getJSONObject(0).getString("value"))
                        ques.putString("answer2", options.getJSONObject(1).getString("value"))
                        ques.putString("answer3", options.getJSONObject(2).getString("value"))
                        ques.putString("answer4", options.getJSONObject(3).getString("value"))
                        a.arguments = ques
                        adapter.addFragment(a, "1")
                        ultraViewPager.adapter = adapter
                        ultraViewPager.initIndicator()
                        ultraViewPager.indicator
                                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                                .setFocusColor(Color.parseColor("#37003f"))
                                .setNormalColor(Color.GRAY)
                                .setRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, resources.displayMetrics).toInt())
                        ultraViewPager.indicator.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM)
                        ultraViewPager.indicator.build()

                        ultraViewPager.setInfiniteLoop(false)
                    }
                }
            }

        })


    }
}
