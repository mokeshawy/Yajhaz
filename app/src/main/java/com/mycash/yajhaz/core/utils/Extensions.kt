package com.mycash.yajhaz.core.utils

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.localize(@StringRes stringRes: Int) = getString(stringRes)

fun Context.localize(@StringRes stringRes: Int, vararg args: Any) = getString(stringRes, *args)


fun View.setBackGroundResource(@DrawableRes resource: Int) =
    this.setBackgroundResource(resource)

fun TextView.setTextViewColor(context: Context, @ColorRes color: Int) =
    this.setTextColor(AppCompatResources.getColorStateList(context, color))


fun String.containsArabicLetters(): Boolean {
    indices.forEach {
        val c = codePointAt(it)
        if (c in 0x0600..0x06E0) return true
        Character.charCount(c)
    }
    return false
}


fun String.isInvalidEmail(): Boolean {
    return !Regex("^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$").matches(this)
}
