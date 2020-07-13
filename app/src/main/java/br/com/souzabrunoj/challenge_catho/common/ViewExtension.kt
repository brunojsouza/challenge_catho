package br.com.souzabrunoj.challenge_catho.common

import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.ImageView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = VISIBLE
}

fun View.gone() {
    this.visibility = GONE
}

fun View.changeVisibility(isVisible: Boolean) {
    takeIf { isVisible }?.let { this.visible() } ?: run { this.gone() }
}

fun ImageView.loadImage(url: String, placeholder: Int = 0) {
    this.load(url) {
        placeholder(placeholder)
        transformations(CircleCropTransformation())
        error(placeholder)
    }
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun BottomSheetDialogFragment.adjustResize() {
    dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
}

fun BottomSheetDialogFragment.setStateExpanded() {
    dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)?.run {
        BottomSheetBehavior.from(this).state = BottomSheetBehavior.STATE_EXPANDED
    }
}