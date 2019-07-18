package com.architecture.www.xmlshowcase

import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_update_user.*

/**
 * Network call to update user details
 * */
internal fun UpdateUser.updateUserDetails(id:String, userValues: JsonObject){
    status.value = true
    val mCompositeDisposable = CompositeDisposable()
    val userResponse = UserService.userServiceApi

    val userResponseList = userResponse.updateUser(id,userValues)
    mCompositeDisposable.add(userResponseList
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(this::handleResponse, this::handleError))
}

/**
 * Handle response send from server
 * */
internal fun UpdateUser.handleResponse(androidList: UserModel) {

    println("AddUser___Result___"+ androidList.name)
    status.value = false
    updateFlag.value = "2"
}

/**
 * Handle error or exception produced by network call
 * */
internal fun UpdateUser.handleError(error: Throwable) {
    Log.d("AddUser___Error___", error.toString())
    status.value = false
    updateFlag.value = "3"
}

/**
 * Network call to get user details from server
 * */
internal fun UpdateUser.getUserDetails(id:String){
    status.value = true
    val mCompositeDisposable = CompositeDisposable()
    val userResponse = UserService.userServiceApi

    val userResponseList = userResponse.getUserById(id)
    mCompositeDisposable.add(userResponseList
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(this::userDetailsResponse, this::userDetailsError))
}

/**
 * Handling user details send from the server
 * */
internal fun UpdateUser.userDetailsResponse(androidList: UserModel) {

    println("AddUser___Result___"+ androidList.name)
    userDetails1.setValue(androidList.name)
    userDetails2.setValue(androidList.age)
    userDetails3.setValue(androidList.address)
    userDetails4.setValue(androidList.description)
    status.value =false
}

/**
 * Handling error exception triggered by the network call
 * */
internal fun UpdateUser.userDetailsError(error: Throwable) {
    Log.d("AddUser___Error___", error.toString())
    status.value =false
}

/**
 * This function will return the user information to the retrofit api class
 * */
internal fun UpdateUser.getUpdate():JsonObject{
    val jsonObject = JsonObject()
    jsonObject.addProperty("name",userDetails1.getValue())
    jsonObject.addProperty("age",userDetails2.getValue())
    jsonObject.addProperty("address",userDetails3.getValue())
    jsonObject.addProperty("description",userDetails4.getValue())
    return jsonObject
}

/**
 * Helper function to hide keyboard after the edit text box lost its focus.
 * */
internal fun UpdateUser.hideKeyBoard(){
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = this.currentFocus
    imm.hideSoftInputFromWindow(view!!.windowToken,0)
}