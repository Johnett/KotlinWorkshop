package com.architecture.www.xmlshowcase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_update_user.*
import kotlinx.android.synthetic.main.app_bar_layout.*

class UpdateUser : AppCompatActivity() {

    private val status = MutableLiveData<Boolean>()
    private val updateFlag = MutableLiveData<String>()
    private var userId:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)

        val bundle :Bundle? = intent!!.extras
        userId = bundle!!.getString("id")

        getUserDetails(userId!!)
        progressView.observe(this,status)
        observe(this,updateFlag)
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
                if(validation()) {
                    updateUserDetails(userId!! ,getUpdate())
                }
                true
            }
        }
        toolbar_title.text = getString(R.string.add_user_toolbar_text)
        layout_shadow.visibility = View.VISIBLE
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

    private fun getUpdate():JsonObject{
        val jsonObject = JsonObject()
        jsonObject.addProperty("name",userDetails1.getValue())
        jsonObject.addProperty("age",userDetails2.getValue())
        jsonObject.addProperty("address",userDetails3.getValue())
        jsonObject.addProperty("description",userDetails4.getValue())
        return jsonObject
    }

    private fun hideKeyBoard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = this.currentFocus
        imm.hideSoftInputFromWindow(view!!.windowToken,0)
    }

    private fun validation():Boolean{
        return when {
            userDetails1.getValue().isEmpty() -> {
                val nameError = "Please enter a name"
                userDetails1.setError(nameError)
                false
            }
            userDetails2.getValue().isEmpty() -> {
                val ageError = "Please enter age"
                userDetails2.setError(ageError)
                false
            }
            userDetails3.getValue().isEmpty()->{
                val addressError="Please enter address"
                userDetails3.setError(addressError)
                false
            }
            userDetails4.getValue().isEmpty()->{
                val descriptionError="Please enter description"
                userDetails4.setError(descriptionError)
                false
            }
            else -> true
        }
    }

    private fun observe(
        owner: LifecycleOwner,
        data: LiveData<String>
    ) = data.observe(owner, Observer {
        if (it=="2"){
            Toast.makeText(this, "User details updated successfully.", Toast.LENGTH_LONG).show()
            hideKeyBoard()
            finish()
        }else if(it=="3"){
            Toast.makeText(this, "Couldn't update the user details; Please try again.", Toast.LENGTH_LONG).show()
            hideKeyBoard()
        }
    })

    private fun getUserDetails(id:String){
        status.value = true
        val mCompositeDisposable = CompositeDisposable()
        val userResponse = UserService.userServiceApi

        val userResponseList = userResponse.getUserById(id)
        mCompositeDisposable.add(userResponseList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::userDetailsResponse, this::userDetailsError))
    }

    private fun userDetailsResponse(androidList: UserModel) {

        println("AddUser___Result___"+ androidList.name)
        userDetails1.setValue(androidList.name)
        userDetails2.setValue(androidList.age)
        userDetails3.setValue(androidList.address)
        userDetails4.setValue(androidList.description)
        status.value =false
    }

    private fun userDetailsError(error: Throwable) {
        Log.d("AddUser___Error___", error.localizedMessage)
        status.value =false
    }

    private fun updateUserDetails(id:String, userValues: JsonObject){
        status.value = true
        val mCompositeDisposable = CompositeDisposable()
        val userResponse = UserService.userServiceApi

        val userResponseList = userResponse.updateUser(id,userValues)
        mCompositeDisposable.add(userResponseList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError))
    }

    private fun handleResponse(androidList: UserModel) {

        println("AddUser___Result___"+ androidList.name)
        status.value = false
        updateFlag.value = "2"
    }

    private fun handleError(error: Throwable) {
        Log.d("AddUser___Error___", error.localizedMessage)
        status.value = false
        updateFlag.value = "3"
    }
}
