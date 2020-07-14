package br.com.souzabrunoj.challenge_catho.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.souzabrunoj.challenge_catho.R
import br.com.souzabrunoj.challenge_catho.common.*
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

const val LIKE_SURVEY = "like"
const val UNLIKE_SURVEY = "dislike"

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
            Toast.makeText(requireContext(), getString(R.string.cv_sent), Toast.LENGTH_SHORT).show()
        }
        ct_Tips.likeClick = {
           viewModel.sendTipSurvey(LIKE_SURVEY)
        }
        ct_Tips.unLikeClick = {
            viewModel.sendTipSurvey(UNLIKE_SURVEY)
        }
    }

    private fun addObservers() {
        viewModel.keysObserver().observe(viewLifecycleOwner, Observer { state ->
            state?.handleIt(
                failure = { handleLoginError() }
            )
        })
        viewModel.loginObserver().observe(viewLifecycleOwner, Observer { state ->
            state?.handleIt(
                success = { bindUserData(it) },
                failure = { handleLoginError() },
                loading = { tv_welcome_label.loading = true },
                stopLoading = { tv_welcome_label.loading = false }
            )
        })

        viewModel.positionsObserver().observe(viewLifecycleOwner, Observer { state ->
            state?.handleIt(
                success = { setupRecyclerView(it) },
                failure = { handleFailure(it) },
                loading = { container_position_loading.visible() },
                stopLoading = { container_position_loading.gone() }
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
        ct_Tips.error = true
    }

    private fun bindUserData(userData: LoginModel) {
        tv_welcome_label.text = getString(R.string.hello).applyBold(userData.name)
        iv_profile_image.loadImage(userData.photoUrl, R.drawable.ic_avatar)
    }

    private fun setCvClick() {
        Toast.makeText(requireContext(), "Send CV Click", Toast.LENGTH_SHORT).show()
    }

    private fun handleLoginError() {
        tv_welcome_label.error = true
        ct_Tips.loading = false
        iv_profile_image.gone()
        ct_Tips.error = true
    }

    private fun handleGetPositionError() {

    }

    private fun handleGetTipsError(failure: Failure) {
        handleFailure(failure)
        ct_Tips.error = true
    }
}