package com.mycash.yajhaz.core.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.provider.Settings
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.mycash.yajhaz.core.token_utils.TokenHandler
import com.mycash.yajhaz.R
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {

    // operation read Android ID from device.


    fun setLayoutParams(dialog: Dialog) {
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window?.attributes)
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window?.attributes = layoutParams
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


    fun ImageView.load(context: Context, image: Any) {
        this.load(image) {
            crossfade(true)
            placeholder(circularProgressDrawable(context))
        }
    }

    private fun circularProgressDrawable(context: Context) =
        CircularProgressDrawable(context).apply {
            strokeWidth = 5f
            centerRadius = 100f
            setColorSchemeColors(ContextCompat.getColor(context, R.color.yalow))
            start()
        }

    fun <T> getLocalizedValue(enValue: T, localeValue: T) = when ((Locale.getDefault().language)) {
        "en" -> enValue
        else -> localeValue
    }



    fun startApplicationSetting(activity: Activity) {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri
        activity.startActivity(intent)
    }

    fun openTheLocationSetting(activity: Activity) {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        activity.startActivity(intent)
    }




    @SuppressLint("SimpleDateFormat")
    fun TokenHandler.handleExpirationDate(expirationDateCallback: () -> Unit) {
        val tokenExpirationDate = getExpirationDate()
        val formatter = SimpleDateFormat("dd-MMM-yyyy")
        val parseDate = formatter.parse(tokenExpirationDate) as Date
        if (System.currentTimeMillis() > parseDate.time) {
            clearToken()
            expirationDateCallback()
        }
    }

}