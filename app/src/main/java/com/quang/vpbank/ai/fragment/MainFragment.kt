package com.quang.vpbank.ai.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.quang.vpbank.ai.R
import com.quang.vpbank.ai.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager)

        viewPager.offscreenPageLimit = 3

        val walletFragment = WalletFragment()
        val planFragment = PlanFragment()
        val libraryFragment = LibraryFragment()
        val communityFragment = CommunityFragment()

        adapter.addFragment(walletFragment, "Wallet")
        adapter.addFragment(planFragment, "Plan")
        adapter.addFragment(libraryFragment, "Library")
        adapter.addFragment(communityFragment, "Community")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

    }

}
