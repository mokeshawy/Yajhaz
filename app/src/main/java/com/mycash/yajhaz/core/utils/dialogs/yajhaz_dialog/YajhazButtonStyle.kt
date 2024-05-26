package com.mycash.yajhaz.core.utils.dialogs.yajhaz_dialog

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

data class YajhazButtonStyle(
    var backGround: Int? = null,
    var icon: Int? = null,
    val iconColor: Int? = null,
    var textColor: Int? = null
) {
    fun applyOnButton(button: MaterialButton) {
        backGround?.let { backGround ->
            button.backgroundTintList = ContextCompat.getColorStateList(button.context, backGround)
            icon?.let { icon ->
                button.icon = ContextCompat.getDrawable(button.context, icon)
            }
            iconColor?.let { iconColor ->
                button.iconTint = ColorStateList.valueOf(iconColor)
            }
            textColor?.let { textColor -> button.setTextColor(textColor) }
        }
    }
}