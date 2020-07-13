package br.com.souzabrunoj.challenge_catho.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import br.com.souzabrunoj.challenge_catho.R
import br.com.souzabrunoj.challenge_catho.common.changeVisibility
import kotlinx.android.synthetic.main.widget_text_view.view.*

class TextViewWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        CardView.inflate(context, R.layout.widget_text_view, this)
        attrs?.run {
            val typeArray = context.obtainStyledAttributes(this, R.styleable.TextViewWidget)
            text = typeArray.getString(R.styleable.TextViewWidget_text)
            loading = typeArray.getBoolean(R.styleable.TextViewWidget_loading, false)
            textColor = typeArray.getColor(R.styleable.TextViewWidget_android_textColor, ContextCompat.getColor(context, R.color.white))
            typeArray.recycle()
        }
    }

    var text: CharSequence? = null
        set(value) {
            value?.let { tv_text.text = it }
            field = value

        }

    var loading: Boolean = false
        set(value) {
            container_loading.changeVisibility(value)
            tv_text.changeVisibility(value.not())
            field = value
        }

    @ColorRes
    private var textColor: Int? = null
        set(value) {
            value?.let { tv_text.setTextColor(it) }
            field = value
        }
}