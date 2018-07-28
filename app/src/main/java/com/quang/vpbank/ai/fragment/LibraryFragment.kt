package com.quang.vpbank.ai.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.quang.vpbank.ai.R
import com.quang.vpbank.ai.adapter.DocumentAdapter
import kotlinx.android.synthetic.main.fragment_library.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LibraryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvDocument.layoutManager = LinearLayoutManager(view.context)
        val listName = ArrayList<String>()
        listName.add("About bank accounts")
        listName.add("About ATM card")
        listName.add("About savings")
        val adapter = DocumentAdapter(listName)
        rvDocument.adapter = adapter
    }


}
