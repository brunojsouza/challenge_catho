package br.com.souzabrunoj.challenge_catho.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.souzabrunoj.challenge_catho.R
import br.com.souzabrunoj.challenge_catho.common.ZoomOutPageTransformer
import br.com.souzabrunoj.challenge_catho.common.applyBold
import br.com.souzabrunoj.challenge_catho.common.loadImage
import br.com.souzabrunoj.challenge_catho.presentation.HomeViewModel
import br.com.souzabrunoj.challenge_catho.ui.adapter.PositionsAdapter
import br.com.souzabrunoj.challenge_catho.ui.base.BaseFragment
import br.com.souzabrunoj.domain.data.response.login.LoginModel
import br.com.souzabrunoj.domain.data.response.position.PositionModel
import br.com.souzabrunoj.domain.data.response.tips.TipModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.random.Random

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPositions()
        viewModel.getTips()
        viewModel.doLogin()
        addObservers()
        setListeners()
    }

    private fun setListeners() {
        ct_Tips.buttonClick = {
            Toast.makeText(requireContext(), "Button Click", Toast.LENGTH_SHORT).show()
        }
        ct_Tips.likeClick = {
            Toast.makeText(requireContext(), "Like Click", Toast.LENGTH_SHORT).show()
        }
        ct_Tips.unLikeClick = {
            Toast.makeText(requireContext(), "UnLike Click", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addObservers() {
        viewModel.positionsObserver().observe(viewLifecycleOwner, Observer { state ->
            state?.handleIt(
                success = { setupRecyclerView(it) },
                failure = { handleFailure(it) }
            )
        })

        viewModel.tipObserver().observe(viewLifecycleOwner, Observer { state ->
            state?.handleIt(
                success = { setupTips(it) },
                failure = { handleFailure(it) },
                loading = { ct_Tips.loading = true },
                stopLoading = { ct_Tips.loading = false }
            )
        })

        viewModel.loginObserver().observe(viewLifecycleOwner, Observer { state ->
            state?.handleIt(
                success = { bindUserData(it) },
                failure = { handleFailure(it) }
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

    private fun setupTips(tips: List<TipModel>) {
        val positions = Random.nextInt(0, tips.size)
        with(tips[positions]) {
            ct_Tips.text = this.description
            ct_Tips.textButton = this.button.label
        }
    }

    private fun bindUserData(userData: LoginModel) {
        tv_welcome_label.text = getString(R.string.hello).applyBold(userData.name)
        iv_profile_image.loadImage(userData.photoUrl, R.drawable.ic_avatar)
    }

    private fun setCvClick() {
        Toast.makeText(requireContext(), "Send CV Click", Toast.LENGTH_SHORT).show()
    }
}