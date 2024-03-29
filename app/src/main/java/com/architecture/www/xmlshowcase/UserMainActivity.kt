package com.architecture.www.xmlshowcase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user_main.*
import kotlinx.android.synthetic.main.app_bar_layout.*

/**
 * Main user activity class
 */
class UserMainActivity : AppCompatActivity(),UserOperations {
    internal lateinit var userResponseList: MutableList<UserModel>
    private var status = MutableLiveData<Boolean>()
    private var activityChangeStatus = MutableLiveData<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)
        progressView.observe(this,status)
        observe(this,activityChangeStatus)
        setUi()
        apiCall()
    }

    override fun deleteUser(id:String) {
        delete(id)
    }

    override fun loadingStatus(loadstatus: Boolean,notifier:RecyclerViewOperation) {
        if (loadstatus){
            status.value = true
            notifier.dataSetChangeAlertI()
        }else{
            Handler().postDelayed({
                status.value = false
            },3000)
        }
    }

    override fun navigateToUpdate(id: String) {
        val intent = Intent(this,UpdateUser::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
        activityChangeStatus.value = true
    }

    private fun setUi(){

        toolbar.run {
            setNavigationIcon(R.drawable.ic_action_close)
            setNavigationOnClickListener { finish() }
        }
        toolbar_title.text = getString(R.string.user_main_activity_toolbar_text)
        layout_shadow.visibility = View.VISIBLE
    }

    internal fun apiCall(){
        status.value = true
        val mCompositeDisposable = CompositeDisposable()
        val userResponse = UserService.userServiceApi
        val movieResponseList = userResponse.getAllUsers()
        mCompositeDisposable.add(movieResponseList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                setResponseList(it)
                status.value = false
            }, {
                it.printStackTrace()
                status.value = false
            }))
    }

    override fun onResume() {
        super.onResume()
        if (activityChangeStatus.value != null){
            if (activityChangeStatus.value as Boolean){
                activityChangeStatus.value = false
            }
        }
    }
}


