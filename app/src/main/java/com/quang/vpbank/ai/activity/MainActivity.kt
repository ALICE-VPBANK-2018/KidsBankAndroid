package com.quang.vpbank.ai.activity

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import com.quang.vpbank.ai.R
import com.quang.vpbank.ai.fragment.MainFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initControl()
    }

    private fun initControl() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_content, MainFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val dialog = AlertDialog.Builder(this)
            dialog.setMessage("Do you want to quit app?")
            dialog.setPositiveButton("Yes") { _, _ -> finish() }
            dialog.setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            dialog.create()
            if (!isFinishing) dialog.show()
        }
        return super.onKeyDown(keyCode, event)
    }
}
