package com.architecture.www.xmlshowcase

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.main_activity_items.view.*

class MainItem(context: Context,
               attrs: AttributeSet? = null):FrameLayout(context,attrs) {
    init {
        inflate(context, R.layout.main_activity_items, this)
        setBackgroundResource(R.drawable.roundedge_background)
    }

    fun setItemImage(resourceId:Int){
        ivItem.setImageResource(resourceId)
    }

    fun setItemText(resourceId: Int){
        tvItem.setText(resourceId)
    }
}