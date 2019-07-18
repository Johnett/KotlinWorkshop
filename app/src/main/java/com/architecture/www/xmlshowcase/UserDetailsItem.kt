package com.architecture.www.xmlshowcase

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.user_details_layout.view.*

/**
 * Custom view
 * */
class UserDetailsItem(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    init {
        orientation = VERTICAL
        inflate(context, R.layout.user_details_layout, this)
    }

    /**
     * Helper class used to set the label
     * */
    fun setLabel(labelText: Int) {
        tvCollectName.setText(labelText)
    }

    /**
     * Helper class used to set the hint
     * */
    fun setHint(hintText: Int) {
        etCollectName.setHint(hintText)
    }

    /**
     * Helper class used to set the error
     * */
    fun setError(error: String) {
        etCollectName.error = error
    }

    /**
     * Helper class used to get the value
     * */
    fun getValue(): String {
        return etCollectName.text.toString()
    }

    /**
     * Helper class used to set the value
     * */
    fun setValue(value:String){
        etCollectName.setText(value)
    }
}