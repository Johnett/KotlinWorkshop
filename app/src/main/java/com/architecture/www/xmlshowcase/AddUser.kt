package com.architecture.www.xmlshowcase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
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
        observe(this,addUserViewModel.checkRequestStatus())
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
                        addUserViewModel.apiCall(
                            addUserViewModel.collectingUserValue(
                                userDetails1.getValue(),
                                userDetails2.getValue(),
                                userDetails3.getValue(),
                                userDetails4.getValue()
                            )
                        )
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

    private fun observe(
        owner: LifecycleOwner,
        data: LiveData<String>
    ) = data.observe(owner, Observer {
        if (it=="2"){
            Toast.makeText(this, "User created successfully; Visit view user page.", Toast.LENGTH_LONG).show()
            hideKeyBoard()
            finish()
        }else if(it=="3"){
            Toast.makeText(this, "Couldn't create the user; Please try again.", Toast.LENGTH_LONG).show()
            hideKeyBoard()
            finish()
        }
    })

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
}
