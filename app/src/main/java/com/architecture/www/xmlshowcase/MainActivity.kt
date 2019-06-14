package com.architecture.www.xmlshowcase

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_layout.*


class MainActivity : AppCompatActivity() {


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUi()
        apiCall()
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
        for (item in androidList){
            System.out.println("MainActivity___Result___"+ item.creation)
        }
    }

    private fun handleError(error: Throwable) {
        Log.d("MainActivity___Error___", error.localizedMessage)
    }
}
