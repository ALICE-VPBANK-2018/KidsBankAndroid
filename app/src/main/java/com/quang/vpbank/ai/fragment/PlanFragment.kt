package com.quang.vpbank.ai.fragment


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quang.vpbank.ai.R
import com.quang.vpbank.ai.adapter.DailySeedAdapter
import com.quang.vpbank.ai.adapter.FamilySharingAdapter
import com.quang.vpbank.ai.model.DailySeed
import com.quang.vpbank.ai.model.FamilySharing
import im.dacer.androidcharts.LineView
import kotlinx.android.synthetic.main.fragment_plan.*

/**
 * A simple [Fragment] subclass.
 *
 */
class PlanFragment : Fragment() {

    var randomint = 9

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val test = ArrayList<String>()
        for (i in 0 until randomint) {
            test.add((i + 1).toString())
        }
        lineView.setBottomTextList(test)
        lineView.setColorArray(intArrayOf(Color.parseColor("#5100fe"), Color.parseColor("#00d6d6"), Color.parseColor("#2196F3"), Color.parseColor("#009688")))
        lineView.setDrawDotLine(true)
        lineView.setShowPopup(LineView.SHOW_POPUPS_NONE)
        randomSet(lineView)

        rvDailySeed.layoutManager = LinearLayoutManager(view.context)
        rvDailySeed.isNestedScrollingEnabled = false
        val listDailySeed = ArrayList<DailySeed>()
        listDailySeed.add(DailySeed("Buy a piano", "-8%", "Detailed plan"))
        listDailySeed.add(DailySeed("Apply for SEM", "-5%", "Detailed plan"))
        val dailySeedAdapter = DailySeedAdapter(listDailySeed)
        rvDailySeed.adapter = dailySeedAdapter

        rvFamilySharing.layoutManager = LinearLayoutManager(view.context)
        rvFamilySharing.isNestedScrollingEnabled = false
        val listFamilySharing = ArrayList<FamilySharing>()
        listFamilySharing.add(FamilySharing("First loan", "June, 9th", "400,000 VND"))
        listFamilySharing.add(FamilySharing("Second loan", "Sep, 9th", "500,000 VND"))
        val familySharingAdapter = FamilySharingAdapter(listFamilySharing)
        rvFamilySharing.adapter = familySharingAdapter
    }

    private fun randomSet(lineView: LineView) {
        val dataList = ArrayList<Int>()
        var random = (Math.random() * 9 + 1).toFloat()
        for (i in 0 until randomint) {
            dataList.add((Math.random() * random).toInt())
        }

        val dataList2 = ArrayList<Int>()
        random = (Math.random() * 9 + 1).toInt().toFloat()
        for (i in 0 until randomint) {
            dataList2.add((Math.random() * random).toInt())
        }

        val dataLists = ArrayList<ArrayList<Int>>()
        dataLists.add(dataList)
        dataLists.add(dataList2)

        lineView.setDataList(dataLists)
    }

}
