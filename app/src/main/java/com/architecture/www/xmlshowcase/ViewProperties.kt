package com.architecture.www.xmlshowcase

import android.view.View

/**
 * This function is used to set the visibility of passing view to visible
 * */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * This function is used to set the visibility of the passing view to gone
 * */
fun View.hide() {
    visibility = View.GONE
}

/**
 * This function is used to set the visibility of the passing view to visible or gone
 * */
fun View.showOrHide(show: Boolean) = if (show) show() else hide()