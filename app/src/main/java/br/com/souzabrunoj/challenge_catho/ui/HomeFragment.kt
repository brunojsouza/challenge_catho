package br.com.souzabrunoj.challenge_catho.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.souzabrunoj.challenge_catho.R
import br.com.souzabrunoj.challenge_catho.presentation.HomeViewModel
import br.com.souzabrunoj.challenge_catho.ui.adapter.PositionsAdapter
import br.com.souzabrunoj.domain.position.Position
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPositions()
        addObservers()
    }

    private fun addObservers() {
        viewModel.positionsObserver().observe(viewLifecycleOwner, Observer { positions ->
            setupRecyclerView(positions)
        })
    }

    private fun setupRecyclerView(list: List<Position>) {
        rv_positions_list.adapter = PositionsAdapter(list)
    }
}