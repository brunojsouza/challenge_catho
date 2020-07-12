package br.com.souzabrunoj.challenge_catho.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.souzabrunoj.challenge_catho.R
import br.com.souzabrunoj.domain.position.Position
import kotlinx.android.synthetic.main.item_position.view.*

class PositionsAdapter(private val listOfPositions: List<Position>) : RecyclerView.Adapter<PositionsAdapter.PositionsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositionsViewHolder {
        return PositionsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_position, parent, false))
    }

    override fun getItemCount(): Int = listOfPositions.size

    override fun onBindViewHolder(holder: PositionsViewHolder, position: Int) {
        holder.bind(listOfPositions[position], position)
    }

    inner class PositionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Position, position: Int) {
            with(itemView) {
                tv_title.text = item.jobAdTile
                tv_date.text = item.date
                tv_company.text = item.company
                tv_location.text = item.getLocation()
                tv_salary.text = if(item.salary.real.isEmpty()) item.salary.range else item.salary.real
                 if(item.showSalary){
                     tv_salary.visibility =VISIBLE
                     iv_show_or_hide_password.setImageResource(R.drawable.ic_show_password)
                } else {
                     tv_salary.visibility = INVISIBLE
                     iv_show_or_hide_password.setImageResource(R.drawable.ic_hide_password)
                }
                iv_show_or_hide_password.setOnClickListener {
                    listOfPositions[position].showSalary = item.showSalary.not()
                    notifyItemChanged(position)
                }
            }
        }
    }
}