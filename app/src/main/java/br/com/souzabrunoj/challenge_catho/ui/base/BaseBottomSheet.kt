package br.com.souzabrunoj.challenge_catho.ui.base

import android.content.DialogInterface
import br.com.souzabrunoj.challenge_catho.R
import br.com.souzabrunoj.challenge_catho.common.adjustResize
import br.com.souzabrunoj.challenge_catho.common.hideKeyboard
import br.com.souzabrunoj.challenge_catho.common.setStateExpanded
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet : BottomSheetDialogFragment() {

    override fun getTheme() = R.style.BottomSheetDialog

    override fun onStart() {
        super.onStart()

        adjustResize()
        setStateExpanded()
    }

    override fun onDismiss(dialog: DialogInterface) {
        view?.hideKeyboard()

        super.onDismiss(dialog)
    }
}