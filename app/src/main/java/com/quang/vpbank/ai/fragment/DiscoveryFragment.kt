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
import com.tmall.ultraviewpager.UltraViewPager
import kotlinx.android.synthetic.main.fragment_discovery.*

/**
 * A simple [Fragment] subclass.
 *
 */
class DiscoveryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discovery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL)
        val a = ContentFragment()
        val b = ContentFragment()
        val c = ContentFragment()
        val d = ContentFragment()
        val e = ContentFragment()
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(a, "1")
        adapter.addFragment(b, "2")
        adapter.addFragment(c, "3")
        adapter.addFragment(d, "4")
        adapter.addFragment(e, "5")
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
