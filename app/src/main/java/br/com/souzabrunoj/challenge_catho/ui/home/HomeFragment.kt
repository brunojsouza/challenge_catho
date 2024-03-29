package br.com.souzabrunoj.challenge_catho.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import br.com.souzabrunoj.challenge_catho.R
import br.com.souzabrunoj.challenge_catho.common.*
import br.com.souzabrunoj.challenge_catho.common.Constants.LIKE_SURVEY
import br.com.souzabrunoj.challenge_catho.common.Constants.UNLIKE_SURVEY
import br.com.souzabrunoj.challenge_catho.presentation.HomeViewModel
import br.com.souzabrunoj.challenge_catho.ui.adapter.PositionsAdapter
import br.com.souzabrunoj.challenge_catho.ui.base.BaseFragment
import br.com.souzabrunoj.domain.common.Failure
import br.com.souzabrunoj.domain.data.response.login.LoginModel
import br.com.souzabrunoj.domain.data.response.position.PositionModel
import br.com.souzabrunoj.domain.data.response.tips.TipModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        setListeners()
    }

    override fun onDestroy() {
        lifecycle.removeObserver(viewModel)
        super.onDestroy()
    }

    private fun setListeners() {
        ct_Tips.buttonClick = {
            viewModel.getTipUrl().takeIf { it.isNotEmpty() }?.let {
                navController.navigate(
                    HomeFragmentDirections.fromHomeToWebVewFragment(
                        url = it
                    )
                )
            }
        }

        ct_Tips.likeClick = { viewModel.sendTipSurvey(LIKE_SURVEY) }

        ct_Tips.unLikeClick = { viewModel.sendTipSurvey(UNLIKE_SURVEY) }

        iv_tips_icon.setOnClickListener { viewModel.showNextTips() }
    }

    private fun addObservers() {
        viewModel.keysObserver().observe(viewLifecycleOwner, Observer { state ->
            state?.handleIt(
                failure = { handleLoginError(it) }
            )
        })
        viewModel.loginObserver().observe(viewLifecycleOwner, Observer { state ->
            state?.handleIt(
                success = { bindUserData(it) },
                failure = { handleLoginError(it) },
                loading = { tv_welcome_label.loading = true },
                stopLoading = { tv_welcome_label.loading = false }
            )
        })

        viewModel.positionsObserver().observe(viewLifecycleOwner, Observer { state ->
            state?.handleIt(
                success = { setupRecyclerView(it) },
                failure = { handleGetPositionError(it) },
                loading = { position_loading.visible() },
                stopLoading = { position_loading.gone() }
            )
        })

        viewModel.tipObserver().observe(viewLifecycleOwner, Observer { state ->
            state?.handleIt(
                success = { setupTip(it) },
                failure = { handleGetTipsError(it) },
                loading = { ct_Tips.loading = true },
                stopLoading = { ct_Tips.loading = false }
            )
        })

        viewModel.likeObserver().observe(viewLifecycleOwner, Observer {
            ct_Tips.changeLikeImageIcon(R.color.green)
            ct_Tips.changeUnLikeImageIcon(R.color.gray)
        })

        viewModel.unlikeObserver().observe(viewLifecycleOwner, Observer {
            ct_Tips.changeUnLikeImageIcon(R.color.red)
            ct_Tips.changeLikeImageIcon(R.color.gray)
        })

        viewModel.showRemoveTipsButtonObserver().observe(viewLifecycleOwner, Observer {
            iv_tips_icon.isVisible = it
        })
    }

    private fun setupRecyclerView(list: List<PositionModel>) {
        vp_positions_list.adapter = PositionsAdapter(list, ::setCvClick)
        TabLayoutMediator(tab_layout, vp_positions_list) { _, _ -> }.attach()
        ZoomOutPageTransformer().apply {
            vp_positions_list.setPageTransformer { page, position -> this.transformPage(page, position) }
        }
    }

    private fun setupTip(tip: TipModel) {
        ct_Tips.text = tip.description
        ct_Tips.textButton = tip.button.label
    }

    private fun bindUserData(userData: LoginModel) {
        tv_welcome_label.text = getString(R.string.hello).applyBold(userData.name)
        iv_profile_image.loadImage(userData.photoUrl, R.drawable.ic_avatar)
    }

    private fun setCvClick() {
        Toast.makeText(requireContext(), "Send CV Click", Toast.LENGTH_SHORT).show()
    }

    private fun handleLoginError(failure: Failure) {
        handleFailure(failure)
        tv_welcome_label.error = true
        ct_Tips.loading = false
        iv_profile_image.gone()
        suggestions_container_error.visible()
        ct_Tips.error = true
    }

    private fun handleGetPositionError(failure: Failure) {
        handleFailure(failure)
        suggestions_container_error.visible()
    }

    private fun handleGetTipsError(failure: Failure) {
        handleFailure(failure)
        ct_Tips.error = true
    }
}