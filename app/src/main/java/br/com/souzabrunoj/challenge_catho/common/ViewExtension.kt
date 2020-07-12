package br.com.souzabrunoj.challenge_catho.common

import android.view.View
import android.widget.ImageView
import coil.api.load
import coil.transform.CircleCropTransformation

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ImageView.loadImage(url: String, placeholder: Int = 0) {
    this.load(url) {
        placeholder(placeholder)
        transformations(CircleCropTransformation())
        error(placeholder)
    }
}