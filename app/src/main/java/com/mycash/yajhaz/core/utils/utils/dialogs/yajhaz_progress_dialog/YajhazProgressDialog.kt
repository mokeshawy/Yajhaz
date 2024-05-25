package com.mycash.yajhaz.core.utils.utils.dialogs.yajhaz_progress_dialog

import android.app.Activity
import android.app.AlertDialog
import com.mycash.yajhaz.core.utils.utils.CommonUtils.setLayoutParams
import com.mycash.yajhaz.databinding.LayoutYajhazProgressDialogBinding
import javax.inject.Inject


class YajhazProgressDialog @Inject constructor(private val activity: Activity) {

    private val binding by lazy { LayoutYajhazProgressDialogBinding.inflate(activity.layoutInflater) }
    private var dialog: AlertDialog? = null

    init {
        buildAlertDialogView()
    }

    private fun buildAlertDialogView(): AlertDialog? {
        val builder = AlertDialog.Builder(activity)
        builder.setView(binding.root)
        dialog = builder.create()
        dialog?.let { setLayoutParams(it) }
        dialog?.setCancelable(false)
        return dialog
    }

    fun show() {
        dialog?.show()
    }

    fun dismiss() {
        dialog?.dismiss()
    }
}