package com.architecture.www.xmlshowcase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_user.*
import kotlinx.android.synthetic.main.app_bar_layout.*

class AddUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

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
                apiCall()
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

    private fun apiCall(){
        val mCompositeDisposable = CompositeDisposable()
        val userResponse = UserService.userServiceApi

        val jsonObj = JsonObject()
        jsonObj.addProperty("name",userDetails1.getValue())
        jsonObj.addProperty("age",userDetails2.getValue())
        jsonObj.addProperty("address",userDetails3.getValue())
        jsonObj.addProperty("description",userDetails4.getValue())

        val userResponseList = userResponse.createUser(jsonObj)
        mCompositeDisposable.add(userResponseList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError))
    }

    private fun handleResponse(androidList: UserModel) {

            System.out.println("AddUser___Result___"+ androidList.name)
    }

    private fun handleError(error: Throwable) {
        Log.d("AddUser___Error___", error.localizedMessage)
    }
}
