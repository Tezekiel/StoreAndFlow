package com.clean.cut.quiztactoe.util

import android.util.DisplayMetrics
import android.view.WindowManager
import kotlin.math.min

fun getDeviceWidth(vm: WindowManager): Int {
  val metrics = DisplayMetrics()
  vm.defaultDisplay.getMetrics(metrics)

  val widthPixels = metrics.widthPixels
  val heightPixels = metrics.heightPixels

  return min(widthPixels, heightPixels)
}
