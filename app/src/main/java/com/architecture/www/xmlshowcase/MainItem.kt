package com.architecture.www.xmlshowcase

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.main_activity_items.view.*

/**
 * This is a custom view which will displayed on the main page.
 * */
class MainItem(context: Context,
               attrs: AttributeSet? = null):FrameLayout(context,attrs) {
    init {
        inflate(context, R.layout.main_activity_items, this)
        setBackgroundResource(R.drawable.roundedge_background)
    }

    /**
     * This is a helper function to set the left icon of the custom view
     * */
    fun setItemImage(resourceId:Int){
        ivItem.setImageResource(resourceId)
    }

    /**
     * This is a helper function to set the custom view text
     * */
    fun setItemText(resourceId: Int){
        tvItem.setText(resourceId)
    }
}