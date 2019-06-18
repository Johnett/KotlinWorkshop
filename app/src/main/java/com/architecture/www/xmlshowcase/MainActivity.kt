package com.architecture.www.xmlshowcase

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_layout.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setUi()
    }

    private fun ScrollView.onScroll(cb: (y: Int) -> Unit) =
        viewTreeObserver.addOnScrollChangedListener { cb(scrollY) }

    override fun onResume() {
        super.onResume()
        layout_shadow.visibility = if (scrollview.scrollY > appToolBar.measuredHeight / 2) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setUi(){

        toolbar.run {
            setNavigationIcon(R.drawable.ic_action_close)
            setNavigationOnClickListener { finish() }
        }
        toolbar_title.text = getString(R.string.main_activity_toolbar_text)

        scrollview.onScroll {
            layout_shadow.visibility =
                if (it > 0f) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
        }
    }
}
