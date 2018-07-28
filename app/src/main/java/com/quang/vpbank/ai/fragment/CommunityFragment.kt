package com.quang.vpbank.ai.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.quang.vpbank.ai.R
import com.quang.vpbank.ai.adapter.NGOsAdapter
import com.quang.vpbank.ai.model.NGOs
import kotlinx.android.synthetic.main.fragment_community.*

/**
 * A simple [Fragment] subclass.
 *
 */
class CommunityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvNGOs.layoutManager = LinearLayoutManager(view.context)
        val listNGOs = ArrayList<NGOs>()
        listNGOs.add(NGOs(R.drawable.ic_poor1, "SYMC Organization . Chicago, 1990", "Save lives by meeting the most critical needs in our communities and investing in breakthrough research to prevent and cure breast cancer."))
        listNGOs.add(NGOs(R.drawable.ic_poor2, "ABCF Organization . Kentucky, 1960", "UNHCR is on the ground doing all we can to reach and protect them but we can’t do this alone."))
        val adapter = NGOsAdapter(listNGOs)
        rvNGOs.adapter = adapter
    }

}
