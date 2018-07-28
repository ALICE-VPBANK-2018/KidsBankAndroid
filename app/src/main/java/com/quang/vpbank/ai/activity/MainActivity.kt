package com.quang.vpbank.ai.activity

import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.WindowManager
import com.quang.vpbank.ai.R
import com.quang.vpbank.ai.fragment.AliceFragment
import com.quang.vpbank.ai.fragment.MainFragment
import jp.co.recruit_lifestyle.android.floatingview.FloatingViewListener
import jp.co.recruit_lifestyle.android.floatingview.FloatingViewManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), FloatingViewListener, NavigationView.OnNavigationItemSelectedListener {
    override fun onFinishFloatingView() {
    }

    override fun onTouchFinished(isFinishing: Boolean, x: Int, y: Int) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initControl()

        val metrics = DisplayMetrics()
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(metrics)
        val inflater = LayoutInflater.from(this)
        val iconView = inflater.inflate(R.layout.widget_chathead, null, false)

        val mFloatingViewManager = FloatingViewManager(this, this)
        val options = FloatingViewManager.Options()
        options.overMargin = (16 * metrics.density).toInt()
        mFloatingViewManager.addViewToWindow(iconView, options)

        iconView.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.layout_content, AliceFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            mFloatingViewManager.removeAllViewToWindow()
        }
    }

    private fun initControl() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {
            R.id.nav_wallet -> {
                val fragmentTransaction = supportFragmentManager!!.beginTransaction()
                fragmentTransaction.replace(R.id.layout_content, MainFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.nav_library -> {
                val fragmentTransaction = supportFragmentManager!!.beginTransaction()
                fragmentTransaction.replace(R.id.layout_content, MainFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}
