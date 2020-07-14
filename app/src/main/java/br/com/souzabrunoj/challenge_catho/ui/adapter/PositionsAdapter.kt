package br.com.souzabrunoj.challenge_catho.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.souzabrunoj.challenge_catho.R
import br.com.souzabrunoj.challenge_catho.ui.adapter.view_holder.PositionsViewHolder
import br.com.souzabrunoj.domain.data.response.position.PositionModel

class PositionsAdapter(private val listOfPositionModels: List<PositionModel>, private val buttonClick: () -> Unit) :
    RecyclerView.Adapter<PositionsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositionsViewHolder {
        return PositionsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_position, parent, false))
    }

    override fun getItemCount(): Int = listOfPositionModels.size

    override fun onBindViewHolder(holder: PositionsViewHolder, position: Int) {
        holder.bind(listOfPositionModels[position], position, listOfPositionModels, buttonClick, this)
    }
}