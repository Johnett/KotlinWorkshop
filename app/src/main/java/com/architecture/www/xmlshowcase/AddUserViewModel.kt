package com.architecture.www.xmlshowcase

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AddUserViewModel:ViewModel(),LifecycleObserver {

    private val isLoading = MutableLiveData<Boolean>()
    private val isComplete = MutableLiveData<String>()
    fun apiCall(userValues:JsonObject){
        isLoading.value = true
        isComplete.value = "1"
        val mCompositeDisposable = CompositeDisposable()
        val userResponse = UserService.userServiceApi

        val userResponseList = userResponse.createUser(userValues)
        mCompositeDisposable.add(userResponseList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError))
    }

    private fun handleResponse(androidList: UserModel) {

        println("AddUser___Result___"+ androidList.name)
        isLoading.value=false
        isComplete.value="2"
    }

    private fun handleError(error: Throwable) {
        Log.d("AddUser___Error___", error.toString())
        isLoading.value=false
        isComplete.value="3"
    }

    /**
     * This function used to check the loading state of progress view
     * */
    fun checkIsLoading():LiveData<Boolean> = isLoading

    /**
     * This function used to check the status of request
     * */
    fun checkRequestStatus():LiveData<String> = isComplete

    fun collectingUserValue(
        name:String,
        age:String,
        address:String,
        description:String
    ):JsonObject{
        val jsonObj = JsonObject()
        jsonObj.addProperty("name",name)
        jsonObj.addProperty("age",age)
        jsonObj.addProperty("address",address)
        jsonObj.addProperty("description",description)
        return jsonObj
    }
}