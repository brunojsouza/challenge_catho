package br.com.souzabrunoj.challenge_catho.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import br.com.souzabrunoj.challenge_catho.R
import br.com.souzabrunoj.challenge_catho.common.changeVisibility
import br.com.souzabrunoj.challenge_catho.common.onClick
import kotlinx.android.synthetic.main.widget_card_tips.view.*

class CardTipsWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    var buttonClick: (onClick)? = null
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
            buttonTextColor = typeArray.getColor(R.styleable.CardTipsWidget_button_text_color, ContextCompat.getColor(context, R.color.white))
            buttonBackground = typeArray.getResourceId(R.styleable.CardTipsWidget_button_background, R.drawable.bg_white_background)
            loading = typeArray.getBoolean(R.styleable.CardTipsWidget_loading,false)
            error = typeArray.getBoolean(R.styleable.CardTipsWidget_error,false)

            typeArray.recycle()
        }
        background = ContextCompat.getDrawable(this@CardTipsWidget.context, R.drawable.bg_white_background)
        setListeners()
    }

    var text: String? = null
        set(value) {
            value?.let { tv_title.text = it }
            field = value
        }

    var textButton: String? = null
        set(value) {
            value?.let { bt_check_cv.text = it }
            field = value
        }

    var loading: Boolean = false
        set(value) {
            sm_container_loading.changeVisibility(value)
            gp_view_container.changeVisibility(value.not())
            field = value
        }

    var error: Boolean = false
        set(value) {
            container_error.changeVisibility(value)
            gp_view_container.changeVisibility(value.not())
            field = value
        }


    @DrawableRes
    var likeIcon: Int? = null
        set(value) {
            value?.let {
                iv_like.setImageResource(it)
            }
            field = value
        }

    @DrawableRes
    var unlikeIcon: Int? = null
        set(value) {
            value?.let {
                iv_unlike.setImageResource(it)
            }
            field = value
        }

    @ColorRes
    var buttonTextColor: Int? = null
    set(value) {
        value?.let { bt_check_cv.setTextColor(it) }
        field = value
    }

    @DrawableRes
    var buttonBackground: Int? = null
        set(value) {
            value?.let { bt_check_cv.background = ContextCompat.getDrawable(context, it)}
            field = value
        }

    private fun setListeners() {
        bt_check_cv.setOnClickListener { buttonClick?.invoke() }
        iv_like.setOnClickListener { likeClick?.invoke() }
        iv_unlike.setOnClickListener { unLikeClick?.invoke() }
    }

    fun changeLikeImageIcon(@ColorRes color: Int){
        iv_like.setColorFilter(ContextCompat.getColor(context, color))
    }

    fun changeUnLikeImageIcon(@ColorRes color: Int){
        iv_unlike.setColorFilter(ContextCompat.getColor(context, color))
    }
}