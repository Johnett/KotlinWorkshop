package com.architecture.www.xmlshowcase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.android.synthetic.main.app_bar_layout.*

class MainPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        setUi()

        item1.setItemImage(R.drawable.ic_add)
        item1.setItemText(R.string.item1_text)
        item2.setItemImage(R.drawable.ic_view)
        item2.setItemText(R.string.item2_text)
    }

    private fun setUi(){

        toolbar.run {
            setNavigationIcon(R.drawable.ic_action_close)
            setNavigationOnClickListener { finish() }
        }
        toolbar_title.text = getString(R.string.main_page_toolbar_text)
        layout_shadow.visibility = View.VISIBLE

        item1.setOnClickListener {
            val intent = Intent(this,AddUser::class.java)
            startActivity(intent)
        }
        item2.setOnClickListener {
            val intent = Intent(this,UserMainActivity::class.java)
            startActivity(intent)
        }
//        user_list.onScroll {
//            layout_shadow.visibility =
//                if (it > 0f) {
//                    View.VISIBLE
//                } else {
//                    View.GONE
//                }
//        }
    }
}
