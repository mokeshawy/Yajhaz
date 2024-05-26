package com.mycash.yajhaz.core.base_fragment

import android.content.Context
import android.net.Network
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.jakewharton.processphoenix.ProcessPhoenix
import com.mycash.yajhaz.R
import com.mycash.yajhaz.core.connectivity.connectivity_manager.ConnectivityManager
import com.mycash.yajhaz.core.connectivity.connectivity_manager.NetworkAwareComponent
import com.mycash.yajhaz.core.error.GeneralException
import com.mycash.yajhaz.core.error.IoException
import com.mycash.yajhaz.core.error.ResponseError
import com.mycash.yajhaz.core.error.ResponseUnAuthorizedError
import com.mycash.yajhaz.core.error.YajhazError
import com.mycash.yajhaz.core.error.YajhazErrorHandler
import com.mycash.yajhaz.core.token_utils.TokenHandler
import com.mycash.yajhaz.core.utils.utils.dialogs.snack_bar.YajhazSnackBarBuilder
import com.mycash.yajhaz.core.utils.utils.dialogs.yajhaz_progress_dialog.YajhazProgressDialog
import com.mycash.yajhaz.core.utils.utils.showToast
import javax.inject.Inject

abstract class BaseFragment<dataBinding : ViewDataBinding> : Fragment(), NetworkAwareComponent {

    abstract val binding: dataBinding

    @Inject
    lateinit var connectivityManager: ConnectivityManager


    @Inject
    lateinit var yajhazProgressDialog: YajhazProgressDialog


    @Inject
    lateinit var yajhazSnackBarBuilder: YajhazSnackBarBuilder


    protected val inputMethodManager
        get() = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager


    @Inject
    protected lateinit var tokenHandler: TokenHandler


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    fun Int.localize() = getString(this)

    fun Int.localize(vararg args: Any) = getString(this, *args)

    fun Int.showShortToast() = requireActivity().showToast(localize(), Toast.LENGTH_SHORT)

    fun showShortToast(message: String) = requireActivity().showToast(message, Toast.LENGTH_SHORT)

    fun Int.showLongToast() = showLongToast(localize())

    fun showLongToast(message: String) = requireActivity().showToast(message)


    fun showProgressDialog() = yajhazProgressDialog.show()

    fun hideProgressDialog() = yajhazProgressDialog.dismiss()


    override fun onNetworkAvailable(network: Network) {

    }

    override fun onNetworkLost(network: Network) {
        //TODO NO NEED SHOW ANY MESSAGE HERE
    }


    protected fun handleApplicationRestart() = ProcessPhoenix.triggerRebirth(requireActivity())


    fun YajhazError.handleError(callback: YajhazError.() -> Unit) {
        (activity as? YajhazErrorHandler)?.handleError(this) {
            when (exception) {
                is GeneralException -> handleGeneralExceptionError()
                is IoException -> handleIoExceptionError()
                is ResponseError -> handleGeneralResponseError()
                is ResponseUnAuthorizedError -> handleUnauthorizedError()
                else -> handleOtherErrors(this)
            }
        }
        callback()
    }


    protected open fun handleOtherErrors(error: YajhazError): YajhazError {
        return error
    }

    protected open fun handleGeneralExceptionError() {
        showShortToast(R.string.genericErrorMessage.localize())
    }

    protected open fun handleIoExceptionError() {
        showShortToast(R.string.genericErrorMessage.localize())
    }

    protected open fun handleGeneralResponseError() {
        showShortToast(R.string.genericErrorMessage.localize())
    }

    protected open fun handleUnauthorizedError() {
        showShortToast(R.string.genericErrorMessage.localize())
    }
}