package com.architecture.www.xmlshowcase

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user_main.*

internal fun UserMainActivity.setRecyclerView(){
    user_list.layoutManager = LinearLayoutManager(this)
    user_list.adapter = UserAdpater(getResponseList(), this,this)
}

internal fun UserMainActivity.setResponseList(responseList:MutableList<UserModel>){
    userResponseList = responseList
    setRecyclerView()
}

internal fun UserMainActivity.getResponseList():MutableList<UserModel>{
    return userResponseList
}

internal fun UserMainActivity.observe(
    owner: LifecycleOwner,
    data: LiveData<Boolean>
) = data.observe(owner, Observer {
    if (it==false){
        apiCall()
    }
})

internal fun UserMainActivity.delete(userId:String){
    val mCompositeDisposable = CompositeDisposable()
    val userResponse = UserService.userServiceApi
    val movieResponseList = userResponse.deleteUser(userId)
    mCompositeDisposable.add(movieResponseList
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe({
            println("testingthedeleteresponse $it")
        }, {
            it.printStackTrace()
            println("testingthedeleteresponse $it")
        }))
}