package br.com.souzabrunoj.challenge_catho.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import br.com.souzabrunoj.challenge_catho.R
import kotlinx.android.synthetic.main.widget_card_tips.view.*

typealias onClick = (() -> Unit)

class CardTipsWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    var checkCvClick: (onClick)? = null
    var likeClick: (onClick)? = null
    var unLikeClick: (onClick)? = null

    init {
        inflate(context, R.layout.widget_card_tips, this)
        attrs?.run {
            val typeArray = context.obtainStyledAttributes(this, R.styleable.CardTipsWidget)
            text = typeArray.getString(R.styleable.CardTipsWidget_text)
            textButton = typeArray.getString(R.styleable.CardTipsWidget_button_text)
            likeIcon = typeArray.getResourceId(R.styleable.CardTipsWidget_like_icon, R.drawable.ic_hide_password)
            unlikeIcon = typeArray.getResourceId(R.styleable.CardTipsWidget_unlike_icon, R.drawable.ic_hide_password)

            typeArray.recycle()
        }
        background = ContextCompat.getDrawable(this@CardTipsWidget.context, R.drawable.bg_white_background)
        setListeners()
    }

    private var text: String? = null
        set(value) {
            value?.let { tv_title.text = it }
            field = value
        }

    private var textButton: String? = null
        set(value) {
            value?.let { bt_check_cv.text = it }
            field = value
        }

    @DrawableRes
    private var likeIcon: Int? = null
        set(value) {
            value?.let { iv_like.background = ContextCompat.getDrawable(this.context, it) }
            field = value
        }

    @DrawableRes
    private var unlikeIcon: Int? = null
        set(value) {
            value?.let { iv_unlike.background = ContextCompat.getDrawable(this.context, it) }
            field = value
        }

    private fun setListeners() {
        bt_check_cv.setOnClickListener { checkCvClick?.invoke() }
        iv_like.setOnClickListener { likeClick?.invoke() }
        iv_unlike.setOnClickListener { unLikeClick?.invoke() }
    }
}