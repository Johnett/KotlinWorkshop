package com.architecture.www.xmlshowcase

import android.view.View

object LeakingSingleton {
  val leakedViews = mutableListOf<View>()
}