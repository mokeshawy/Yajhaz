package com.mycash.yajhaz.core.utils.utils.dialogs.yabraa_dialog

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat
import androidx.core.text.toSpanned
import androidx.core.view.isVisible
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mycash.yajhaz.R
import com.mycash.yajhaz.core.utils.utils.CommonUtils
import com.mycash.yajhaz.core.utils.utils.localize
import com.mycash.yajhaz.databinding.LayoutYajhazDialogBinding

class YajhazDialogBuilder(activity: Activity, @StyleRes style: Int = R.style.CustomDialogTheme) :
    MaterialAlertDialogBuilder(activity, style) {


    private lateinit var binding: LayoutYajhazDialogBinding

    private var title: Spanned? = null
    private var message: Spanned? = null
    private var messageInfo: CharSequence? = null
    private var icon: Int? = null
    private var topButtonText: String? = null
    private var bottomButtonText: String? = null
    private var topButtonAction: (() -> Unit)? = null
    private var bottomButtonAction: (() -> Unit)? = null
    private var topButtonVisibility : Boolean? = null
    private var bottomButtonVisibility : Boolean? = null
    private var topButtonStyle: YajhazButtonStyle? = null
    private var bottomButtonStyle: YajhazButtonStyle? = null
    private var topButtonActionDismiss: Boolean? = null
    private var bottomButtonActionDismiss: Boolean? = null


    override fun create(): AlertDialog {
        binding = LayoutYajhazDialogBinding.inflate(LayoutInflater.from(context))
        setView(binding.root)
        val dialog = super.create()
        CommonUtils.setLayoutParams(dialog)
        binding.setUpViews(dialog)
        return dialog
    }

    private fun LayoutYajhazDialogBinding.setUpViews(dialog: Dialog) {
        title?.let { initializeTitleText(it) }
        message?.let { initializeMessageText(it) }
        messageInfo?.let { initializeMessageInfoText(it) }
        icon?.let { initializeIcon(it) }
        handleTopButton(dialog)
        handleBottomButton(dialog)
    }

    fun addHtmlMessage(message: Int, vararg values: Any): YajhazDialogBuilder {
        val localizedMessage = context.localize(message, *values)
        val spanned = HtmlCompat.fromHtml(localizedMessage, HtmlCompat.FROM_HTML_MODE_LEGACY)
        val spannedBuilder = SpannableStringBuilder()
        if (this.message != null) {
            this.message = spannedBuilder.append(this.message).append("\n").append(spanned)
        } else {
            this.message = spanned
        }

        return this
    }

    fun setTitleStringFormat(title: Int, vararg values: Any): YajhazDialogBuilder {
        this.title = context.localize(title, *values).toSpanned()
        return this
    }

    fun setMessageStringFormat(message: Int, vararg value: Any): YajhazDialogBuilder {
        this.message = context.localize(message, *value).toSpanned()
        return this
    }

    override fun setTitle(titleId: Int): YajhazDialogBuilder {
        title = context.localize(titleId).toSpanned()
        return this
    }

    private fun LayoutYajhazDialogBinding.initializeTitleText(title: Spanned) {
        titleTv.visibility = View.VISIBLE
        titleTv.text = title
    }

    override fun setMessage(messageId: Int): YajhazDialogBuilder {
        message = context.localize(messageId).toSpanned()
        return this
    }

    private fun LayoutYajhazDialogBinding.initializeMessageText(message: Spanned) {
        messageTv.visibility = View.VISIBLE
        messageTv.text = message
    }

    override fun setMessage(message: CharSequence?): YajhazDialogBuilder {
        messageInfo = message
        return this
    }

    private fun LayoutYajhazDialogBinding.initializeMessageInfoText(message: CharSequence) {
        messageInfoTv.visibility = View.VISIBLE
        messageInfoTv.text = message
    }

    override fun setIcon(iconId: Int): YajhazDialogBuilder {
        icon = iconId
        return this
    }

    private fun LayoutYajhazDialogBinding.initializeIcon(icon: Int) {
        icon.let {
            iconIv.visibility = View.VISIBLE
            iconIv.setImageResource(it)
        }
    }

    override fun setPositiveButton(
        text: CharSequence?, listener: DialogInterface.OnClickListener?
    ): MaterialAlertDialogBuilder {
        throw UnsupportedOperationException()
    }

    override fun setNegativeButton(
        text: CharSequence?, listener: DialogInterface.OnClickListener?
    ): MaterialAlertDialogBuilder {
        throw UnsupportedOperationException()
    }

    override fun setNeutralButton(
        text: CharSequence?, listener: DialogInterface.OnClickListener?
    ): MaterialAlertDialogBuilder {
        throw UnsupportedOperationException()
    }

    fun setTopButton(
        topButtonText: Int,
        topButtonStyle: YajhazButtonStyle? = null,
        dialogDismiss: Boolean = true,
        topButtonVisibility: Boolean = true,
        topButton: () -> Unit
    ): YajhazDialogBuilder {
        this.topButtonStyle = topButtonStyle
        this.topButtonText = context.localize(topButtonText)
        this.topButtonActionDismiss = dialogDismiss
        this.topButtonAction = topButton
        this.topButtonVisibility = topButtonVisibility
        return this
    }


    fun setBottomButton(
        bottomButtonText: Int,
        bottomButtonStyle: YajhazButtonStyle? = null,
        dialogDismiss: Boolean = true,
        bottomButtonVisibility: Boolean = true,
        bottomButtonAction: () -> Unit = {}
    ): YajhazDialogBuilder {
        this.bottomButtonStyle = bottomButtonStyle
        this.bottomButtonText = context.localize(bottomButtonText)
        this.bottomButtonActionDismiss = dialogDismiss
        this.bottomButtonAction = bottomButtonAction
        this.bottomButtonVisibility = bottomButtonVisibility
        return this
    }

    private fun LayoutYajhazDialogBinding.handleTopButton(dialog: Dialog) {
        topButton.isVisible = topButtonText != null
        topButton.isVisible = topButtonVisibility == true
        topButtonStyle?.applyOnButton(topButton)
        topButton.text = topButtonText.toString()
        setOnTopButtonClicked(dialog)
    }

    private fun LayoutYajhazDialogBinding.setOnTopButtonClicked(dialog: Dialog) {
        topButton.setOnClickListener {
            topButtonAction?.invoke()
            if (topButtonActionDismiss == true) {
                dialog.dismiss()
            }
        }
    }

    private fun LayoutYajhazDialogBinding.handleBottomButton(dialog: Dialog) {
        bottomButton.isVisible = bottomButtonText != null
        bottomButton.isVisible = bottomButtonVisibility == true
        bottomButtonStyle?.applyOnButton(bottomButton)
        bottomButton.text = bottomButtonText.toString()
        setOnBottomButtonClicked(dialog)
    }

    private fun LayoutYajhazDialogBinding.setOnBottomButtonClicked(dialog: Dialog) {
        bottomButton.setOnClickListener {
            bottomButtonAction?.invoke()
            if (bottomButtonActionDismiss == true) {
                dialog.dismiss()
            }
        }
    }
}