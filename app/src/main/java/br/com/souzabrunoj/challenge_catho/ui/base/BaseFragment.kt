package br.com.souzabrunoj.challenge_catho.ui.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import br.com.souzabrunoj.challenge_catho.R
import br.com.souzabrunoj.challenge_catho.widgets.bottom_sheet.ButtonAction
import br.com.souzabrunoj.challenge_catho.widgets.bottom_sheet.InformationBottomSheet
import br.com.souzabrunoj.domain.common.Failure

open class BaseFragment : Fragment() {

    protected val navController: NavController by lazy { findNavController() }

    protected fun showInformationBottomSheet(
        title: CharSequence = getString(R.string.generic_title),
        description: String,
        textButton: String = getString(R.string.ok),
        callback: ButtonAction? = null
    ) {
        InformationBottomSheet.newInstance(textButton = textButton, title = title, description = description, buttonClick = callback)
            .show(childFragmentManager, this::class.java.simpleName)
    }

    protected fun handleFailure(failure: Failure) {
        showInformationBottomSheet(description = failure.message)
    }
}