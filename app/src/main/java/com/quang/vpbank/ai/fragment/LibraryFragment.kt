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

        adapter.setOnItemClickListener { _, _ ->
            val fragmentTransaction = parentFragment!!.fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.layout_content, DiscoveryFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        quiz1.setOnClickListener { quiz() }
        quiz2.setOnClickListener { quiz() }
        quiz3.setOnClickListener { quiz() }
        quiz4.setOnClickListener { quiz() }
    }

    private fun quiz() {
        val fragmentTransaction = parentFragment!!.fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.layout_content, QuizFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}
