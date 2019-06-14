package com.architecture.www.xmlshowcase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user_main.*
import kotlinx.android.synthetic.main.app_bar_layout.*

class UserMainActivity : AppCompatActivity() {
    private lateinit var userResponseList: List<UserModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)

        setUi()
        apiCall()
    }

    private fun setUi(){

        toolbar.run {
            setNavigationIcon(R.drawable.ic_action_close)
            setNavigationOnClickListener { finish() }
        }
        toolbar_title.text = getString(R.string.user_main_activity_toolbar_text)
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

    private fun apiCall(){
        val mCompositeDisposable = CompositeDisposable()
        val userResponse = UserService.userServiceApi
        val movieResponseList = userResponse.getAllUsers()
        mCompositeDisposable.add(movieResponseList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError))
    }

    private fun handleResponse(androidList: List<UserModel>) {
        setResponseList(androidList)
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
    }

    private fun setRecyclerView(){
        user_list.layoutManager = LinearLayoutManager(this)
        user_list.adapter = UserAdpater(getResponseList(), this)

    }

    private fun setResponseList(responseList:List<UserModel>){
        userResponseList = responseList
        setRecyclerView()
    }

    private fun getResponseList():List<UserModel>{
        return userResponseList
    }
}


