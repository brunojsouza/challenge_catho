package br.com.souzabrunoj.challenge_catho.ui.adapter.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.souzabrunoj.challenge_catho.R
import br.com.souzabrunoj.challenge_catho.ui.adapter.PositionsAdapter
import br.com.souzabrunoj.domain.common.getStringFromResources
import br.com.souzabrunoj.domain.data.response.position.PositionModel
import kotlinx.android.synthetic.main.item_position.view.*

class PositionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: PositionModel, position: Int, listOfPositionModels: List<PositionModel>, buttonClick: () -> Unit, adapter: PositionsAdapter) {
        with(itemView) {
            tv_title.text = item.jobAdTile
            tv_date.text = item.date
            tv_company.text = item.company
            tv_location.text = item.getLocation()
            tv_salary.text = if (item.salaryModel.real.isEmpty()) item.salaryModel.range else item.salaryModel.real
            if (item.showSalary) {
                iv_cover_up_password.visibility = View.INVISIBLE
                iv_show_or_hide_password.setImageResource(R.drawable.ic_show_password)
            } else {
                iv_cover_up_password.visibility = View.VISIBLE
                iv_show_or_hide_password.setImageResource(R.drawable.ic_hide_password)
            }
            iv_show_or_hide_password.setOnClickListener {
                listOfPositionModels[position].showSalary = item.showSalary.not()
                adapter.notifyItemChanged(position)
            }
            bt_send_cv.setOnClickListener {
                buttonClick.invoke()
                bt_send_cv.text = getStringFromResources(R.string.cv_sent)
            }
        }
    }
}