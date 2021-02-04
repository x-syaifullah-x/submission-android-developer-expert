package com.android.developer.expert.extension

import android.content.res.Resources
import kotlin.math.ceil
import kotlin.math.roundToInt

fun Int.toDp() = ceil(this / Resources.getSystem().displayMetrics.density).roundToInt()
fun Int.toPx() = (this * Resources.getSystem().displayMetrics.density).roundToInt()