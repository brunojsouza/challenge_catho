package br.com.souzabrunoj.challenge_catho.common

import android.text.SpannableStringBuilder
import androidx.core.text.bold

fun String.applyBold(boldText: String) = SpannableStringBuilder()
    .append(this)
    .append(" ")
    .bold { append(boldText) }
