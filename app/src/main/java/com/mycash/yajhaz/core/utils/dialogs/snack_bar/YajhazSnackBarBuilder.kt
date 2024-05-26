package com.mycash.yajhaz.core.utils.dialogs.snack_bar


import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import com.mycash.yajhaz.R
import com.mycash.yajhaz.databinding.LayoutYajhazSnackbarBinding
import javax.inject.Inject


class YajhazSnackBarBuilder @Inject constructor(private val activity: Activity) {

    private lateinit var binding: LayoutYajhazSnackbarBinding
    private var message: Int? = null
    private var messageInfo: String? = null
    private var startIcon: Int? = null
    private var endIcon: Int? = null
    private var duration: Int? = null
    private var startActionButton: (View) -> Unit = {}
    private var endActionButton: (View) -> Unit = {}

    fun build(view: View): Snackbar {

        val inflater = activity.layoutInflater
        binding = LayoutYajhazSnackbarBinding.inflate(inflater)

        val snackBar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)

        message?.let { binding.messageTv.text = activity.resources.getString(it) }

        messageInfo?.let { binding.messageTv.text = it }

        binding.initializeStartActionBtn(snackBar)
        startIcon?.let { binding.initializeStartIcon(it) }

        binding.initializeEndActionBtn(snackBar)
        endIcon?.let { binding.initializeEndIcon(it) }

        duration?.let { snackBar.duration = it }

        snackBar.setSnackBarView()
        return snackBar
    }

    fun setMessage(@StringRes message: Int): YajhazSnackBarBuilder {
        this.message = message
        return this
    }

    fun setMessageInfo(messageInfo: String): YajhazSnackBarBuilder {
        this.messageInfo = messageInfo
        return this
    }

    fun setStartActionBtn(startActionBtn: (View) -> Unit = {}): YajhazSnackBarBuilder {
        this.startActionButton = startActionBtn
        return this
    }

    private fun LayoutYajhazSnackbarBinding.initializeStartActionBtn(snackBar: Snackbar) {
        startActionBtn.setOnClickListener {
            startActionButton.invoke(it)
            snackBar.dismiss()
        }
    }

    fun setStartIcon(startIcon: Int): YajhazSnackBarBuilder {
        this.startIcon = startIcon
        return this
    }

    private fun LayoutYajhazSnackbarBinding.initializeStartIcon(startIcon: Int?) {
        startIcon?.let {
            startActionBtn.visibility = View.VISIBLE
            startActionBtn.setImageResource(it)
        }
    }

    fun setEndActionBtn(endActionBtn: (View) -> Unit = {}): YajhazSnackBarBuilder {
        this.endActionButton = endActionBtn
        return this
    }

    private fun LayoutYajhazSnackbarBinding.initializeEndActionBtn(snackBar: Snackbar) {
        endActionBtn.setOnClickListener {
            endActionButton.invoke(it)
            snackBar.dismiss()
        }
    }

    fun setEndIcon(endIcon: Int): YajhazSnackBarBuilder {
        this.endIcon = endIcon
        return this
    }

    private fun LayoutYajhazSnackbarBinding.initializeEndIcon(endIcon: Int?) {
        endIcon?.let {
            endActionBtn.visibility = View.VISIBLE
            endActionBtn.setImageResource(it)
        }
    }

    fun setDuration(duration: Int): YajhazSnackBarBuilder {
        this.duration = duration
        return this
    }

    @SuppressLint("RestrictedApi")
    private fun Snackbar.setSnackBarView() {
        val snackBarLayout = view as? Snackbar.SnackbarLayout
        snackBarLayout?.apply {
            setBackgroundColor(Color.TRANSPARENT)
            view.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            setPadding(
                snackBarLayout.context.resources.getDimension(R.dimen.xsml).toInt(), 0,
                snackBarLayout.context.resources.getDimension(R.dimen.xsml).toInt(), 0
            )
            addView(binding.root, 0)
        }
    }
}
