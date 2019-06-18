package com.architecture.www.xmlshowcase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add_user.*
import kotlinx.android.synthetic.main.app_bar_layout.*

class AddUser : AppCompatActivity() {

    private lateinit var addUserViewModel:AddUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        addUserViewModel = ViewModelProviders.of(this).get(AddUserViewModel::class.java)
        lifecycle.run {
            addObserver(addUserViewModel)
        }

        progressView.observe(this,addUserViewModel.checkIsLoading())
        setUi()
        setAllCollectors()
    }

    private fun setUi(){
        toolbar.run {
            inflateMenu(R.menu.menu_add_user)
            setNavigationIcon(R.drawable.ic_action_close)
            setNavigationOnClickListener { finish() }
            val menu = toolbar.menu.findItem(R.id.commit)
            menu.setOnMenuItemClickListener {
                    addUserViewModel.apiCall(
                    addUserViewModel.collectingUserValue(
                                    userDetails1.getValue(),
                                    userDetails2.getValue(),
                                    userDetails3.getValue(),
                                    userDetails4.getValue())
                )
                true
            }
        }
        toolbar_title.text = getString(R.string.add_user_toolbar_text)
        layout_shadow.visibility = View.VISIBLE

//        user_list.onScroll {
//            layout_shadow.visibility =
//                if (it > 0f) {
//                    View.VISIBLE
//                } else {
//                    View.GONE
//                }
//        }
    }

    private fun setAllCollectors(){
        userDetails1.setLabel(R.string.add_user_name_label)
        userDetails1.setHint(R.string.add_user_name_hint)
        userDetails2.setLabel(R.string.add_user_age_label)
        userDetails2.setHint(R.string.add_user_age_hint)
        userDetails3.setLabel(R.string.add_user_address_label)
        userDetails3.setHint(R.string.add_user_address_hint)
        userDetails4.setLabel(R.string.add_user_description_label)
        userDetails4.setHint(R.string.add_user_description_hint)
    }

}
