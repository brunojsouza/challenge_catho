package br.com.souzabrunoj.challenge_catho.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.souzabrunoj.challenge_catho.R
import br.com.souzabrunoj.domain.common.getStringFromResources
import br.com.souzabrunoj.domain.data.response.position.PositionModel
import kotlinx.android.synthetic.main.item_position.view.*

class PositionsAdapter(private val listOfPositionModels: List<PositionModel>, private val buttonClick: () -> Unit) :
    RecyclerView.Adapter<PositionsAdapter.PositionsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositionsViewHolder {
        return PositionsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_position, parent, false))
    }

    override fun getItemCount(): Int = listOfPositionModels.size

    override fun onBindViewHolder(holder: PositionsViewHolder, position: Int) {
        holder.bind(listOfPositionModels[position], position)
    }

    inner class PositionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PositionModel, position: Int) {
            with(itemView) {
                tv_title.text = item.jobAdTile
                tv_date.text = item.date
                tv_company.text = item.company
                tv_location.text = item.getLocation()
                tv_salary.text = if(item.salaryModel.real.isEmpty()) item.salaryModel.range else item.salaryModel.real
                 if(item.showSalary){
                     iv_cover_up_password.visibility = INVISIBLE
                     iv_show_or_hide_password.setImageResource(R.drawable.ic_show_password)
                } else {
                     iv_cover_up_password.visibility = VISIBLE
                     iv_show_or_hide_password.setImageResource(R.drawable.ic_hide_password)
                }
                iv_show_or_hide_password.setOnClickListener {
                    listOfPositionModels[position].showSalary = item.showSalary.not()
                    notifyItemChanged(position)
                }
                bt_send_cv.setOnClickListener {
                    buttonClick.invoke()
                    bt_send_cv.text = getStringFromResources(R.string.cv_sent)
                }
            }
        }
    }
}