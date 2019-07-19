package com.architecture.www.xmlshowcase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_update_user.*
import kotlinx.android.synthetic.main.app_bar_layout.*

/**
 * This class extend AppCompatActivity and it helps to update the user information.
 * User update endpoint called with the help of retrofit library
 * */
class UpdateUser : AppCompatActivity() {

    internal val status = MutableLiveData<Boolean>()
    internal val updateFlag = MutableLiveData<String>()
    private var userId:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)

        val bundle :Bundle? = intent?.extras
        userId = bundle?.getString("id")

        userId?.let { getUserDetails(it) }
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
                    userId?.let { it1 -> updateUserDetails(it1,getUpdate()) }
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
}
