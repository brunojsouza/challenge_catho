package br.com.souzabrunoj.challenge_catho.widgets.bottom_sheet

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.souzabrunoj.challenge_catho.R
import br.com.souzabrunoj.challenge_catho.ui.base.BaseBottomSheet
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.widget_information_bottom_sheet.*

typealias ButtonAction = () -> Unit

class InformationBottomSheet : BaseBottomSheet() {

    private var buttonClick: ButtonAction? = null
    private val args by lazy { Args.fromBundle(arguments) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.widget_information_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_title.text = args.title
        tv_description.text = args.description
        bt_ok.text = args.textButton

        bt_ok.setOnClickListener {
            buttonClick?.invoke()
            dismiss()
        }
    }

    companion object {
        fun newInstance(
            buttonClick: ButtonAction? = null,
            textButton: CharSequence,
            title: CharSequence,
            description: CharSequence
        ): InformationBottomSheet {
            return InformationBottomSheet().apply {
                this.arguments = Args(textButton, title, description).toBundle()
                this.buttonClick = buttonClick
            }
        }
    }


    @Parcelize
    private data class Args(
        val textButton: CharSequence = "",
        val title: CharSequence = "",
        val description: CharSequence =""
    ) : Parcelable {

        fun toBundle() = Bundle().also { it.putParcelable(ARGS, this) }

        companion object {
            private const val ARGS = "args"

            fun fromBundle(args: Bundle?) = args?.getParcelable(ARGS) ?: Args()
        }
    }
}