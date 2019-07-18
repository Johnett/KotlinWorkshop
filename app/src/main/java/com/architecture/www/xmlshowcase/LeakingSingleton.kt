package com.architecture.www.xmlshowcase

import android.view.View

/**
 * This class sole purpose is to mock an android leak
 * */
object LeakingSingleton {
  val leakedViews = mutableListOf<View>()
}