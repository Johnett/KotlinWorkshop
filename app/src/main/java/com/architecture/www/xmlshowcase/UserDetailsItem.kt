package com.architecture.www.xmlshowcase

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.user_details_layout.view.*

class UserDetailsItem(contex: Context,
                      attrs:AttributeSet?=null ):LinearLayout(contex,attrs) {
    init {
        orientation = VERTICAL
        inflate(context,R.layout.user_details_layout,this)
    }

    fun setLabel(labelText:Int){
        tvCollectName.setText(labelText)
    }
    fun setHint(hintText:Int){
        etCollectName.setHint(hintText)
    }

    fun getValue():String{
        return etCollectName.text.toString()
    }
}