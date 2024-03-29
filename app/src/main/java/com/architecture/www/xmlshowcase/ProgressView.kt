package com.architecture.www.xmlshowcase

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * CustomView which display a loading view when some process is working on background
 */
class ProgressView(context: Context,
                   attr:AttributeSet?):FrameLayout(context,attr) {
    companion object {
        private const val SHOW_DELAY_MS = 1000L
    }

    private val showRunnable = Runnable { show() }
    private val delayHandler = Handler()

    init {
        hide() // hide self by default
        inflate(context, R.layout.progress_view_layout, this)
        isClickable = true
        isFocusable = true
    }

    /**
     * Observe function watch the changes happen to the data object(which is a boolean variable)
     */
    fun observe(
        owner: LifecycleOwner,
        data: LiveData<Boolean>
    ) = data.observe(owner, Observer {
        setIsLoading(it)
    })

    private fun setIsLoading(isLoading: Boolean) {
        delayHandler.removeCallbacks(showRunnable)
        if (isLoading) {
            delayHandler.postDelayed(showRunnable, SHOW_DELAY_MS)
        } else {
            hide()
        }
    }
}